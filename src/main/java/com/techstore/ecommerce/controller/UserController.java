package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.constant.SuccessMessage;
import com.techstore.ecommerce.object.dto.filter.UserFilter;
import com.techstore.ecommerce.object.dto.request.UserRequest;
import com.techstore.ecommerce.object.dto.response.UserResponse;
import com.techstore.ecommerce.object.entity.jpa.User;
import com.techstore.ecommerce.object.mapper.UserMapper;
import com.techstore.ecommerce.object.model.AuthenticationInfo;
import com.techstore.ecommerce.object.wrapper.AbstractResponse;
import com.techstore.ecommerce.object.wrapper.SuccessResponse;
import com.techstore.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    AbstractResponse getAllUser(@RequestBody Optional<UserFilter> filter) {
        Page<UserResponse> response =
                service.findAllUsers(filter.orElse(new UserFilter())).map(mapper::toResponseModel);

        return new SuccessResponse<>(response, SuccessMessage.FIND_ALL_USERS.getMessage());
    }

    @GetMapping("/{userId}")
    AbstractResponse getUserById(@PathVariable int userId) {
        UserResponse response = mapper.toResponseModel(service.findUserById(userId));
        return new SuccessResponse<>(response, SuccessMessage.FIND_USER_BY_ID.getMessage() + userId);
    }

    @GetMapping("/_search/auth/{username}")
    AbstractResponse getUserByUsername(@PathVariable String username) {
        // TODO: can kiem tra lai
        AuthenticationInfo response = mapper.toAuthInfo((User) service.loadUserByUsername(username));

        return new SuccessResponse<>(
                response, SuccessMessage.FIND_USER_BY_USERNAME.getMessage() + username);
    }

    @PostMapping
    AbstractResponse createUser(@RequestBody @Valid UserRequest request) {
        UserResponse response = mapper.toResponseModel(service.createUser(request));
        return new SuccessResponse<>(
                response, HttpStatus.CREATED.value(), SuccessMessage.CREATE_USER.getMessage());
    }

    @PutMapping("/{userId}")
    AbstractResponse updateUser(@PathVariable int userId, @RequestBody @Valid UserRequest request) {
        UserResponse response = mapper.toResponseModel(service.updateUser(userId, request));
        return new SuccessResponse<>(response, SuccessMessage.UPDATE_USER.getMessage());
    }

    @PatchMapping("/{userId}/activate")
    AbstractResponse activateUser(@PathVariable long userId) {
        service.activateUser(userId);
        return new SuccessResponse<>(null, SuccessMessage.ACTIVATE_USER.getMessage());
    }

    @PatchMapping("/{userId}/deactivate")
    AbstractResponse deactivateUser(@PathVariable long userId) {
        service.deactivateUser(userId);
        return new SuccessResponse<>(null, SuccessMessage.DEACTIVATE_USER.getMessage());
    }
}
