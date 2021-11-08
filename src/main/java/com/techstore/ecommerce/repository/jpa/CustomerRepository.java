package com.techstore.ecommerce.repository.jpa;

import com.techstore.ecommerce.object.entity.jpa.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerRepository extends
        JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String Email);

    boolean existsByPhoneNumber(String phoneNumber);
}
