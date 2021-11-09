package com.techstore.ecommerce.service;

import com.techstore.ecommerce.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.dto.filter.UserFilter;
import com.techstore.ecommerce.object.dto.request.UserRequest;
import com.techstore.ecommerce.object.entity.jpa.Role;
import com.techstore.ecommerce.object.entity.jpa.User;
import com.techstore.ecommerce.object.mapper.UserMapper;
import com.techstore.ecommerce.repository.jpa.UserRepository;
import com.techstore.ecommerce.repository.jpa.spec.UserSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    public Page<User> findAllUsers(UserFilter filter) {
        Specification<User> specification = UserSpec.getSpecification(filter);
        System.out.println(filter.getPagination().getPageAndSort());
        return userRepo.findAll(specification, filter.getPagination().getPageAndSort());
    }

    public User findUserById(long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found any user with id " + id));
    }

    public boolean validateUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    public User createUser(UserRequest request) {
        User user = userMapper.createEntityFromRequest(request);

        if (userRepo.existsByUsername(user.getUsername())) {
            throw new EntityExistsException("Username " + user.getUsername() + " already exists");
        }
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("Email " + user.getEmail() + " already exists");
        }
        if (userRepo.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new EntityExistsException("Phone number " + user.getPhoneNumber() + " already exists");
        }
        encryptPassword(user);

        Role role = roleService.findRoleById(request.getRoleId());
        user.setRole(role);
        // TODO: save image from multipart file
        user.setImage("123");
        System.out.println(user);

        return userRepo.save(user);
    }

    public User updateUser(long id, UserRequest request) {
        User user = findUserById(id);

        if (!user.getUsername().equals(request.getUsername())) {
            throw new AccessDeniedException("Unable to modify username");
        }
        if (!user.getEmail().equals(request.getEmail()) && userRepo.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("Email " + user.getEmail() + " already exists");
        }
        if (!user.getPhoneNumber().equals(request.getPhoneNumber()) && userRepo.existsByPhoneNumber(
                user.getPhoneNumber())) {
            throw new EntityExistsException("Phone number " + user.getPhoneNumber() + " already exists");
        }
        userMapper.update(user, request);
        encryptPassword(user);

        Role role = roleService.findRoleById(request.getRoleId());
        user.setRole(role);
        // TODO: save image from multipart file
        user.setImage("123");

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

    private void encryptPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username " + username + " not found"));

    }
}
