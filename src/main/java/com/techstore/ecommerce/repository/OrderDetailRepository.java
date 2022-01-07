package com.techstore.ecommerce.repository;

import com.techstore.ecommerce.object.entity.jpa.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderDetailRepository extends
        JpaRepository<OrderDetail, Long>, JpaSpecificationExecutor<OrderDetail> {
}
