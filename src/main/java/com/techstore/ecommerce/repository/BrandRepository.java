package com.techstore.ecommerce.repository;

import com.techstore.ecommerce.object.entity.jpa.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BrandRepository extends
        JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand> {
    boolean existsByName(String name);
}
