package com.techstore.ecommerce.repository.spec;

import com.techstore.ecommerce.object.dto.filter.ReceiptFilter;
import com.techstore.ecommerce.object.entity.jpa.Product;
import com.techstore.ecommerce.object.entity.jpa.Receipt;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

public final class ReceiptSpec {

    public static Specification<Receipt> getSpecification(ReceiptFilter filter) {
        return Specification.where(hasReceiptCode(filter.getReceiptCode()));
    }

    private static Specification<Receipt> hasReceiptCode(String receiptCode) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(receiptCode)
                        ? builder.conjunction()
                        : builder.like(root.get("receiptCode"), "%" + receiptCode + "%");
    }


    private static Specification<Product> hasPriceBetween(BigDecimal from, BigDecimal to) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(from) && ObjectUtils.isEmpty(to)
                        ? builder.conjunction()
                        : ObjectUtils.isEmpty(from)
                        ? builder.lessThanOrEqualTo(root.get("details").get("price"), to)
                        : ObjectUtils.isEmpty(to)
                        ? builder.greaterThanOrEqualTo(root.get("details").get("price"), from)
                        : builder.between(root.get("details").get("price"), from, to);
    }

}
