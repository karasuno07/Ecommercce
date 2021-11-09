package com.techstore.ecommerce.service;

import com.techstore.ecommerce.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.dto.filter.UserFilter;
import com.techstore.ecommerce.object.dto.request.UserRequest;
import com.techstore.ecommerce.object.entity.jpa.User;
import com.techstore.ecommerce.object.mapper.UserMapper;
import com.techstore.ecommerce.repository.jpa.UserRepository;
import com.techstore.ecommerce.repository.jpa.spec.UserSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;

    public Page<User> findAllUsers(UserFilter filter) {
        Specification<User> specification = UserSpec.getSpecification(filter);
        return userRepo.findAll(specification, filter.getPagination().getPageAndSort());
    }

    public User findUserById(long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found any user with id " + id));
    }

    public User createUser(UserRequest request) {
        User user = userMapper.createEntityFromRequest(request);

        validateUser(user);

        // TODO: save image from multipart file

        return userRepo.save(user);
    }

    public User updateUser(long id, UserRequest request) {
        User user = findUserById(id);
        userMapper.update(user, request);

        validateUser(user);
        // TODO: save image from multipart file

        return userRepo.save(user);
    }

    public void activateUser(long id) {
        User user = findUserById(id);
        user.setActive(true);
        userRepo.save(user);
    }

    public void deactivateUser(long id) {
        User user = findUserById(id);
        user.setActive(false);
        userRepo.save(user);
    }

    public void validateUser(User user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new EntityExistsException("Username " + user.getUsername() + " already exists");
        }

        if (userRepo.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("Email " + user.getEmail() + " already exists");
        }

        if (userRepo.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new EntityExistsException("Phone number " + user.getPhoneNumber() + " already exists");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username " + username + " not found"));
    }
}
