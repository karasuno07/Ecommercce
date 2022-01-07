package com.techstore.ecommerce.service;

import com.techstore.ecommerce.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.dto.filter.UserFilter;
import com.techstore.ecommerce.object.dto.request.UserRequest;
import com.techstore.ecommerce.object.entity.jpa.Role;
import com.techstore.ecommerce.object.entity.jpa.User;
import com.techstore.ecommerce.object.mapper.UserMapper;
import com.techstore.ecommerce.repository.UserRepository;
import com.techstore.ecommerce.repository.spec.UserSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityExistsException;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userJpaRepo;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;


    public Page<User> findAllUsers(UserFilter filter) {

        Page<User> users;
        Specification<User> specification = UserSpec.getSpecification(filter);
        users = userJpaRepo.findAll(specification, filter.getPagination().getPageAndSort());
        log.info("USE DB: " + users);
        return users;
    }

    public User findUserById(long id) {
        return userJpaRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found any user with id " + id));
    }

    public boolean validateUsername(String username) {
        return userJpaRepo.existsByUsername(username);
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
