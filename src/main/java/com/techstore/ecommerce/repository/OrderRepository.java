package com.techstore.ecommerce.repository;

import com.techstore.ecommerce.object.entity.jpa.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends
        JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
}
