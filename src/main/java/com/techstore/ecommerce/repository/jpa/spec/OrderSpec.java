package com.techstore.ecommerce.repository.jpa.spec;

import com.techstore.ecommerce.object.dto.filter.OrderFilter;
import com.techstore.ecommerce.object.dto.filter.ProductFilter;
import com.techstore.ecommerce.object.entity.jpa.Order;
import com.techstore.ecommerce.object.entity.jpa.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

public final class OrderSpec {

    public static Specification<Order> getSpecification(OrderFilter filter) {
        return Specification.where(hasOrderCode(filter.getOrderCode()));
    }

    private static Specification<Order> hasOrderCode(String orderCode) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(orderCode)
                        ? builder.conjunction()
                        : builder.like(root.get("orderCode"), "%" + orderCode + "%");
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
