package com.techstore.ecommerce.object.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OrderDetail {

    @JsonProperty("product_id")
    @NotNull(message = "null")
    private int productId;

    @NotNull(message = "null")
    private int quantity;

    @NotNull(message = "null")
    private BigDecimal price;
}
