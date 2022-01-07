package com.techstore.ecommerce.repository;

import com.techstore.ecommerce.object.entity.jpa.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductReviewRepository extends
        JpaRepository<ProductReview, Long>, JpaSpecificationExecutor<ProductReview> {
}
