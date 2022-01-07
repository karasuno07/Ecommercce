package com.techstore.ecommerce.repository;

import com.techstore.ecommerce.object.entity.jpa.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReceiptRepository extends
        JpaRepository<Receipt, Long>, JpaSpecificationExecutor<Receipt> {
}
