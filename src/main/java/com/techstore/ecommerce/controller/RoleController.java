package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.dto.mapper.RoleMapper;
import com.techstore.ecommerce.object.dto.request.RoleRequest;
import com.techstore.ecommerce.object.dto.response.RoleResponse;
import com.techstore.ecommerce.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RoleController {

    private final RoleService service;
    private final RoleMapper mapper;

//    @PreAuthorize("hasAuthority('ROLE_READ')")
    @GetMapping
    ResponseEntity<?> getAllRoles() {
        List<RoleResponse> response = service.findAllRoles().stream()
                                             .map(mapper::toResponseModel)
                                             .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

//    @PreAuthorize("hasAuthority('ROLE_READ')")
    @GetMapping("/{roleId}")
    ResponseEntity<?> getRoleById(@PathVariable long roleId) {
        RoleResponse response = mapper.toResponseModel(service.findRoleById(roleId));

        return ResponseEntity.ok(response);
    }

//    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    @PostMapping
    ResponseEntity<?> createRole(@RequestBody RoleRequest request) {
        RoleResponse response = mapper.toResponseModel(service.createRole(request));
        return ResponseEntity.ok(response);
    }

//    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    @PutMapping("/{roleId}")
    ResponseEntity<?> updateRole(@PathVariable long roleId, @RequestBody RoleRequest request) {
        RoleResponse response = mapper.toResponseModel(service.updateRole(roleId, request));
        return ResponseEntity.ok(response);
    }

//    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    @DeleteMapping("/{roleId}")
    ResponseEntity<?> deleteRole(@PathVariable long roleId) {
        service.deleteRole(roleId);
        return ResponseEntity.ok().build();
    }
}
