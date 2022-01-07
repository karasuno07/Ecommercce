package com.techstore.ecommerce.repository;

import com.techstore.ecommerce.object.entity.jpa.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SupplierRepository extends
        JpaRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {
    boolean existsByName(String name);
}
