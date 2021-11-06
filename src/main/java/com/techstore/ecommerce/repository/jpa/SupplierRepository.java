package com.techstore.ecommerce.repository.jpa;

import com.techstore.ecommerce.object.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SupplierRepository extends
        JpaRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {
}
