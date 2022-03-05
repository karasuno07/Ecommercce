package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.dto.filter.UserFilter;
import com.techstore.ecommerce.object.dto.mapper.UserMapper;
import com.techstore.ecommerce.object.dto.request.UserRequest;
import com.techstore.ecommerce.object.dto.response.UserResponse;
import com.techstore.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @PreAuthorize("hasAuthority('USER_READ')")
    @GetMapping
    ResponseEntity<?> getAllUser(@RequestBody Optional<UserFilter> filter) {
        Page<UserResponse> response =
                service.findAllUsers(filter.orElse(new UserFilter())).map(mapper::toResponseModel);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('USER_READ') OR hasRole('CUSTOMER')")
    @GetMapping("/{userId}")
    ResponseEntity<?> getUserById(@PathVariable int userId) {
        UserResponse response = mapper.toResponseModel(service.findUserById(userId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/_search/{username}")
    ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        boolean valid = service.validateUsername(username);
        return ResponseEntity.ok(valid);
    }

    //    @PreAuthorize("hasAuthority('USER_CREATE')")
    @PostMapping
    ResponseEntity<?> createUser(@RequestBody @Valid UserRequest request) {
        UserResponse response = mapper.toResponseModel(service.createUser(request));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('USER_UPDATE') OR hasRole('CUSTOMER')")
    @PutMapping("/{userId}")
    ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody @Valid UserRequest request) {
        UserResponse response = mapper.toResponseModel(service.updateUser(userId, request));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @PatchMapping("/{userId}/activate")
    ResponseEntity<?> activateUser(@PathVariable long userId) {
        service.activateUser(userId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @PatchMapping("/{userId}/deactivate")
    ResponseEntity<?> deactivateUser(@PathVariable long userId) {
        service.deactivateUser(userId);
        return ResponseEntity.ok().build();
    }
}
