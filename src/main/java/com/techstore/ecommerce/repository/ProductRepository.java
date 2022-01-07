package com.techstore.ecommerce.repository;

import com.techstore.ecommerce.object.entity.jpa.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends
        JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("SELECT o FROM Product o LEFT JOIN FETCH o.details AS d "
            + "WHERE o.id = ?1 AND d.isDefault = true")
    Optional<Product> findDefaultProductById(Long id);

    boolean existsByName(String name);
}
