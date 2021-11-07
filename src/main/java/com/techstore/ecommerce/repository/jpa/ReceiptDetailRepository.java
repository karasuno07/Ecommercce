package com.techstore.ecommerce.repository.jpa;

import com.techstore.ecommerce.object.entity.jpa.ReceiptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReceiptDetailRepository extends
        JpaRepository<ReceiptDetail, Long>, JpaSpecificationExecutor<ReceiptDetail> {
}
