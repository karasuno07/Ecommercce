package com.techstore.ecommerce.service;

import com.techstore.ecommerce.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.constant.Authorities;
import com.techstore.ecommerce.object.dto.request.RoleRequest;
import com.techstore.ecommerce.object.entity.jpa.Role;
import com.techstore.ecommerce.object.mapper.RoleMapper;
import com.techstore.ecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
//        getAuthorities(role, request);

        return roleRepo.save(role);
    }

    public Role updateRole(long id, RoleRequest request) {
        Role role = findRoleById(id);
        roleMapper.update(role, request);
//        getAuthorities(role, request);
        System.out.println(role);
        return roleRepo.save(role);
    }

    public void deleteRole(long id) {
        Role role = findRoleById(id);
        roleRepo.delete(role);
    }

//    private void getAuthorities(Role role, RoleRequest request) {
//        if (ObjectUtils.isEmpty(role.getAuthorities())) role.setAuthorities(new ArrayList<>());
//        if (!ObjectUtils.isEmpty(request.getAuthorities())) {
//            Set<String> set = request.getAuthorities().stream()
//                                     .fiter(this::isValidAuthority)
//                                     .collect(Collectors.toSet());
//            role.getAuthorities().clear();l
//            role.getAuthorities().addAll(set);
//        }
//    }

    private boolean isValidAuthority(String value) {
        boolean valid = Arrays.stream(Authorities.values())
                              .anyMatch(e -> e.getPermission().equalsIgnoreCase(value));
        if (!valid) {
            throw new ResourceNotFoundException("Invalid Permission");
        } else {
            return true;
        }
    }
}
