package com.techstore.ecommerce.service;

import com.techstore.ecommerce.object.dto.mapper.RoleMapper;
import com.techstore.ecommerce.object.dto.request.RoleRequest;
import com.techstore.ecommerce.object.entity.jpa.Role;
import com.techstore.ecommerce.object.exception.ResourceNotFoundException;
import com.techstore.ecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepo;
    private final RoleMapper roleMapper;

    public List<Role> findAllRoles() {
        return roleRepo.findAll();
    }

    public Role findRoleById(long id) {
        return roleRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found role with id " + id));
    }

    public Role createRole(RoleRequest request) {
        Role role = roleMapper.createEntityFromRequest(request);
        return roleRepo.save(role);
    }

    public Role updateRole(long id, RoleRequest request) {
        Role role = findRoleById(id);
        roleMapper.update(role, request);
        return roleRepo.save(role);
    }

    public void deleteRole(long id) {
        Role role = findRoleById(id);
        roleRepo.delete(role);
    }
}
