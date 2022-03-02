package com.techstore.ecommerce.repository;

import com.techstore.ecommerce.object.entity.jpa.ReceiptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptDetailRepository extends
        JpaRepository<ReceiptDetail, Long>, JpaSpecificationExecutor<ReceiptDetail> {
}
