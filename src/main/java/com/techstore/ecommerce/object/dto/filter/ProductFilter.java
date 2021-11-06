package com.techstore.ecommerce.object.dto.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techstore.ecommerce.object.model.Pagination;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductFilter {

    private String name;

    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("price_from")
    private BigDecimal priceFrom;

    @JsonProperty("price_to")
    private BigDecimal priceTo;

    private Boolean active;

    private Pagination pagination = new Pagination(10);
}
