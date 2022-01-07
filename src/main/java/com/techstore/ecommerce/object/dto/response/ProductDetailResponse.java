package com.techstore.ecommerce.object.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class ProductDetailResponse {

    @JsonProperty("in_stock")
    private int inStock;

    private BigDecimal price;

    private BigDecimal discount;

    private Map<String, String> descriptions;

    private List<String> images;
}
