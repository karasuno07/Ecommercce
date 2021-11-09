package com.techstore.ecommerce.object.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techstore.ecommerce.object.model.Address;
import com.techstore.ecommerce.object.model.FullName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderRequest {

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("full_name")
    @NotNull(message = "null")
    private FullName recipientName;

    @JsonProperty("phone_number")
    @NotBlank(message = "blank")
    private String phoneNumber;

    @NotBlank(message = "blank")
    private String email;

    @NotBlank(message = "blank")
    private Address address;

    @NotEmpty(message = "empty")
    private List<OrderDetailRequest> details;
}
