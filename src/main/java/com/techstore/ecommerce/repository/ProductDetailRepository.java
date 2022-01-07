package com.techstore.ecommerce.repository;

import com.techstore.ecommerce.object.entity.jpa.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductDetailRepository extends
        JpaRepository<ProductDetail, Long>, JpaSpecificationExecutor<ProductDetail> {
}
