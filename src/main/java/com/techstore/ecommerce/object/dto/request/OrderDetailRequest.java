package com.techstore.ecommerce.object.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OrderDetailRequest {

    @JsonProperty("product_id")
    @NotNull(message = "null")
    private int productId;

    @NotNull(message = "null")
    private BigDecimal price;

    @NotNull(message = "null")
    private int quantity;
}
