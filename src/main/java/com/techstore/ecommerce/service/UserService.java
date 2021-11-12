package com.techstore.ecommerce.service;

import com.techstore.ecommerce.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.dto.filter.UserFilter;
import com.techstore.ecommerce.object.dto.request.UserRequest;
import com.techstore.ecommerce.object.entity.es.UserES;
import com.techstore.ecommerce.object.entity.jpa.Role;
import com.techstore.ecommerce.object.entity.jpa.User;
import com.techstore.ecommerce.object.mapper.UserMapper;
import com.techstore.ecommerce.repository.es.UserESRepository;
import com.techstore.ecommerce.repository.jpa.UserRepository;
import com.techstore.ecommerce.repository.jpa.spec.UserSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final ElasticsearchRestTemplate restTemplate;

    private final UserRepository userJpaRepo;
    private final UserESRepository userESRepo;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @PostConstruct
    protected void validateData() {
//        long jpaCount = userJpaRepo.count();
//        long esCount = userESRepo.count();
//        if (esCount != jpaCount) {
        log.info("ON LOAD USER DATA FROM JPA TO ES...");
        userESRepo.deleteAll();
        List<UserES> data = userJpaRepo.findAll().stream()
                                       .map(userMapper::jpaToEsEntity)
                                       .collect(Collectors.toList());
        System.out.println(data);
//            userESRepo.saveAll(data);
//        }
    }

    public Page<User> findAllUsers(UserFilter filter) {

        Page<User> users;

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        if (!ObjectUtils.isEmpty(filter.getUsername())) {
            // wildcard // = LIKE SQL * chuỗi *
            queryBuilder.should(wildcardQuery("username", "*" + filter.getUsername() + "*"));
        }
        if (!ObjectUtils.isEmpty(filter.getFullName())) {
            // match => áp dụng full text search => tận dụng đánh index của es
            queryBuilder.should(matchQuery("full_name", filter.getFullName()));
        }
        if (!ObjectUtils.isEmpty(filter.getGender())) {
            queryBuilder.should(matchQuery("gender", filter.getGender()));
        }
        if (!ObjectUtils.isEmpty(filter.getEmail())) {
            // term => tìm kiếm chuỗi nguyên gốc => chuỗi chính xác
            queryBuilder.should(termQuery("email", filter.getEmail()));
        }
        if (!ObjectUtils.isEmpty(filter.getAddress())) {
            queryBuilder.should(matchQuery("address", filter.getAddress()));
        }
        if (!ObjectUtils.isEmpty(filter.getActive())) {
            queryBuilder.should(matchQuery("active", filter.getActive()));
        }
        if (!ObjectUtils.isEmpty(filter.getDobTo()) && !ObjectUtils.isEmpty(filter.getDobTo())) {
            queryBuilder.should(rangeQuery("date_of_birth")
                                        .format("date_option_time")
                                        .gte(filter.getDobFrom())
                                        .lte(filter.getDobTo()));
        } else if (!ObjectUtils.isEmpty(filter.getDobFrom()) && ObjectUtils.isEmpty(filter.getDobTo())) {
            queryBuilder.should(rangeQuery("date_of_birth")
                                        .format("date_option_time")
                                        .gte(filter.getDobFrom()));
        } else if (ObjectUtils.isEmpty(filter.getDobTo()) && !ObjectUtils.isEmpty(filter.getDobTo())) {
            queryBuilder.should(rangeQuery("date_of_birth")
                                        .format("date_option_time")
                                        .lte(filter.getDobTo()));
        }

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(filter.getPagination().getPageAndSort())
                .build();
        SearchHits<UserES> hits =
                restTemplate.search(searchQuery, UserES.class, IndexCoordinates.of("users"));

        if (hits.hasSearchHits()) {
            SearchPage<UserES> page =
                    SearchHitSupport.searchPageFor(hits, filter.getPagination().getPageAndSort());
            users = new PageImpl<>(page.getContent().stream()
                                       .map(SearchHit::getContent)
                                       .map(userMapper::esToJpaEntity)
                                       .collect(Collectors.toList()),
                                   page.getPageable(),
                                   page.getTotalElements());
            log.info("FOUND: " + users);
        } else { // fallback trong trường hợp tìm kiếm trong eslaticsearch không ra kết quả
            // thì chạy xuống postgres để tìm kiếm
            Specification<User> specification = UserSpec.getSpecification(filter);
            users = userJpaRepo.findAll(specification, filter.getPagination().getPageAndSort());
            log.info("USE DB: " + users);
        }

        return users;
    }

    public User findUserById(long id) {
        UserES userES = userESRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found any user with id " + id));
        return userMapper.esToJpaEntity(userES);
    }

    public boolean validateUsername(String username) {
        return userESRepo.existsByUsername(username);
    }

    public User createUser(UserRequest request) {
        User user = userMapper.createEntityFromRequest(request);

        if (userJpaRepo.existsByUsername(user.getUsername())) {
            throw new EntityExistsException("Username " + user.getUsername() + " already exists");
        }
        if (userJpaRepo.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("Email " + user.getEmail() + " already exists");
        }
        if (userJpaRepo.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new EntityExistsException("Phone number " + user.getPhoneNumber() + " already exists");
        }
        encryptPassword(user);

        Role role = roleService.findRoleById(request.getRoleId());
        user.setRole(role);
        // TODO: save image from multipart file
        user.setImage("123");

        return userJpaRepo.save(user);
    }

    public User updateUser(long id, UserRequest request) {
        User user = findUserById(id);

        if (!user.getUsername().equals(request.getUsername())) {
            throw new AccessDeniedException("Unable to modify username");
        }
        if (!user.getEmail().equals(request.getEmail()) && userJpaRepo.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("Email " + user.getEmail() + " already exists");
        }
        if (!user.getPhoneNumber().equals(request.getPhoneNumber()) && userJpaRepo.existsByPhoneNumber(
                user.getPhoneNumber())) {
            throw new EntityExistsException("Phone number " + user.getPhoneNumber() + " already exists");
        }
        userMapper.update(user, request);
        encryptPassword(user);

        Role role = roleService.findRoleById(request.getRoleId());
        user.setRole(role);
        // TODO: save image from multipart file
        user.setImage("123");

        return userJpaRepo.save(user);
    }

    public void activateUser(long id) {
        User user = findUserById(id);
        user.setActive(true);
        userJpaRepo.save(user);
    }

    public void deactivateUser(long id) {
        User user = findUserById(id);
        user.setActive(false);
        userJpaRepo.save(user);
    }

    private void encryptPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userJpaRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username " + username + " not found"));

    }
}
