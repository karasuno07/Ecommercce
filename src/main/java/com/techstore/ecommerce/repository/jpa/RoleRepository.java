package com.techstore.ecommerce.repository.jpa;

import com.techstore.ecommerce.object.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository extends
        JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
}
