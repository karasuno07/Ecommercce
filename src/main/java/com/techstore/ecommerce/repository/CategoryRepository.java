package com.techstore.ecommerce.repository;

import com.techstore.ecommerce.object.entity.jpa.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends
        JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    boolean existsByName(String name);
}
