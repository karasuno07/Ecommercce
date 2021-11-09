package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.constant.SuccessMessage;
import com.techstore.ecommerce.object.dto.request.RoleRequest;
import com.techstore.ecommerce.object.dto.response.RoleResponse;
import com.techstore.ecommerce.object.mapper.RoleMapper;
import com.techstore.ecommerce.object.wrapper.AbstractResponse;
import com.techstore.ecommerce.object.wrapper.SuccessResponse;
import com.techstore.ecommerce.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;
    private final RoleMapper mapper;

    @GetMapping
    AbstractResponse getAllRoles() {
        List<RoleResponse> response = service.findAllRoles().stream()
                                             .map(mapper::toResponseModel)
                                             .collect(Collectors.toList());

        return new SuccessResponse<>(response, SuccessMessage.FIND_ALL_ROLES.getMessage());
    }

    @GetMapping("/{roleId}")
    AbstractResponse getRoleById(@PathVariable long roleId) {
        RoleResponse response = mapper.toResponseModel(service.findRoleById(roleId));

        return new SuccessResponse<>(response, SuccessMessage.FIND_ROLE_BY_ID.getMessage() + roleId);
    }

    @PostMapping
    AbstractResponse createRole(@RequestBody RoleRequest request) {
        RoleResponse response = mapper.toResponseModel(service.createRole(request));

        return new SuccessResponse<>(response, SuccessMessage.CREATE_ROLE.getMessage());
    }

    @PutMapping("/{roleId}")
    AbstractResponse updateRole(@PathVariable long roleId, @RequestBody RoleRequest request) {
        RoleResponse response = mapper.toResponseModel(service.updateRole(roleId, request));

        return new SuccessResponse<>(response, SuccessMessage.UPDATE_ROLE.getMessage());
    }

    @DeleteMapping("/{roleId}")
    AbstractResponse deleteRole(@PathVariable long roleId) {
        service.deleteRole(roleId);

        return new SuccessResponse<>(null, SuccessMessage.DELETE_ROLE.getMessage());
    }
}
