package com.techstore.ecommerce.object.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailResponse {

    private long id;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_category")
    private String productCategory;

    @JsonProperty("product_brand")
    private String productBrand;

    private int quantity;

    private BigDecimal price;
}
