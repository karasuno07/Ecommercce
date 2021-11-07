package com.techstore.ecommerce.repository.jpa.spec;

import com.techstore.ecommerce.object.dto.filter.ProductFilter;
import com.techstore.ecommerce.object.entity.jpa.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

public final class ProductSpec {

    public static Specification<Product> getSpecification(ProductFilter filter) {
        return Specification.where(hasName(filter.getName()))
                            .and(hasBrandName(filter.getBrandName()))
                            .and(hasCategoryName(filter.getCategoryName()))
                            .and(isActive(filter.getActive()));
    }

    private static Specification<Product> hasName(String name) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(name)
                ? builder.conjunction() // Lấy tất cả nếu tham số truyền vào rỗng
                : builder.like(root.get("name"), "%" + name + "%");
    }

    private static Specification<Product> hasBrandName(String brandName) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(brandName)
                ? builder.conjunction()
                : builder.like(root.get("brand").get("name"), "%" + brandName + "%");
    }

    private static Specification<Product> hasCategoryName(String categoryName) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(categoryName)
                ? builder.conjunction()
                : builder.like(root.get("category").get("name"), "%" + categoryName + "%");
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

    private static Specification<Product> isActive(Boolean active) {
        return (root, query, builder) ->
                ObjectUtils.isEmpty(active)
                ? builder.conjunction()
                : builder.equal(root.get("active"), active);
    }
}
