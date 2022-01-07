package com.techstore.ecommerce.object.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailResponse {

    private long id;

    private ProductResponse product;

    private int quantity;

    private BigDecimal price;
}
