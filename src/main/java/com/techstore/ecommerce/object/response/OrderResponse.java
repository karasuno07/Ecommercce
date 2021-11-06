package com.techstore.ecommerce.object.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {

    private long id;

    @JsonProperty("full_name")
    private String recipientName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String email;

    private String address;

//    private List<OrderDetailResponse> details;
}
