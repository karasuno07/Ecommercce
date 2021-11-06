package com.techstore.ecommerce.object.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReceiptDetailResponse {
    private long id;

    @JsonProperty("product_name")
    private String productName;

    private int quantity;

    private BigDecimal price;
}
