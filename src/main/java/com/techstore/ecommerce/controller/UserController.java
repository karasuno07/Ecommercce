package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.constant.SuccessMessage;
import com.techstore.ecommerce.object.dto.filter.UserFilter;
import com.techstore.ecommerce.object.dto.request.UserRequest;
import com.techstore.ecommerce.object.dto.response.UserResponse;
import com.techstore.ecommerce.object.mapper.UserMapper;
import com.techstore.ecommerce.object.wrapper.AbstractResponse;
import com.techstore.ecommerce.object.wrapper.SuccessResponse;
import com.techstore.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @PreAuthorize("hasAuthority('USER_READ')")
    @GetMapping
    AbstractResponse getAllUser(@RequestBody Optional<UserFilter> filter) {
        Page<UserResponse> response =
                service.findAllUsers(filter.orElse(new UserFilter())).map(mapper::toResponseModel);

        return new SuccessResponse<>(response, SuccessMessage.FIND_ALL_USERS.getMessage());
    }

    @PreAuthorize("hasAuthority('USER_READ') OR hasRole('CUSTOMER')")
    @GetMapping("/{userId}")
    AbstractResponse getUserById(@PathVariable int userId) {
        UserResponse response = mapper.toResponseModel(service.findUserById(userId));
        return new SuccessResponse<>(response, SuccessMessage.FIND_USER_BY_ID.getMessage() + userId);
    }

    @GetMapping("/_search/{username}")
    AbstractResponse getUserByUsername(@PathVariable String username) {
        boolean valid = service.validateUsername(username);

        return new SuccessResponse<>(valid, null);
    }

//    @PreAuthorize("hasAuthority('USER_CREATE')")
    @PostMapping
    AbstractResponse createUser(@RequestBody @Valid UserRequest request) {
        UserResponse response = mapper.toResponseModel(service.createUser(request));
        return new SuccessResponse<>(
                response, HttpStatus.CREATED.value(), SuccessMessage.CREATE_USER.getMessage());
    }

    @PreAuthorize("hasAuthority('USER_UPDATE') OR hasRole('CUSTOMER')")
    @PutMapping("/{userId}")
    AbstractResponse updateUser(@PathVariable int userId, @RequestBody @Valid UserRequest request) {
        UserResponse response = mapper.toResponseModel(service.updateUser(userId, request));
        return new SuccessResponse<>(response, SuccessMessage.UPDATE_USER.getMessage());
    }

    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @PatchMapping("/{userId}/activate")
    AbstractResponse activateUser(@PathVariable long userId) {
        service.activateUser(userId);
        return new SuccessResponse<>(null, SuccessMessage.ACTIVATE_USER.getMessage());
    }

    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @PatchMapping("/{userId}/deactivate")
    AbstractResponse deactivateUser(@PathVariable long userId) {
        service.deactivateUser(userId);
        return new SuccessResponse<>(null, SuccessMessage.DEACTIVATE_USER.getMessage());
    }
}
