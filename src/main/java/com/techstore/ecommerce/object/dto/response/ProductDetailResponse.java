package com.techstore.ecommerce.object.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techstore.ecommerce.object.model.ProductProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDetailResponse {

    @JsonProperty("in_stock")
    private int inStock;

    private BigDecimal price;

    private BigDecimal discount;

    private ProductProperty descriptions;

    private List<String> images;
}
