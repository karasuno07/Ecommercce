package com.techstore.ecommerce.object.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class ReceiptDetailRequest {

    @JsonProperty("product_id")
    @NotNull(message = "null")
    private int productId;

    @NotNull(message = "null")
    private BigDecimal price;

    @NotEmpty(message = "empty")
    private Map<String, String> descriptions;

    @NotNull(message = "null")
    private int quantity;
}
