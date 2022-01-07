package com.techstore.ecommerce.object.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {

    private long id;

    @JsonProperty("order_code")
    private String orderCode;

    private String name;

    private String slug;

    private CategoryResponse category;

    private BrandResponse brand;

    private List<ProductDetailResponse> details;

    private List<ProductReviewResponse> reviews;

    private boolean available;
}
