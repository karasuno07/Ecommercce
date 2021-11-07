package com.techstore.ecommerce.object.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ReceiptRequest {

    @JsonProperty("supplier_id")
    @NotNull(message = "null")
    private int supplierId;

    @JsonProperty("user_id")
    @NotNull(message = "null")
    private int userId;

    @NotEmpty(message = "empty")
    private List<ReceiptDetailRequest> details;

}
