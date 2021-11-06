package com.techstore.ecommerce.object.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReceiptResponse {

    private long id;

    private String receiptCode;

    @JsonProperty("user_id")
    private int userId;
}
