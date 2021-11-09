package com.techstore.ecommerce.object.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
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

    @JsonProperty("last_shipping_date")
    private Date lastShippingDate;

    @JsonProperty("last_receive_date")
    private Date lastReceiveDate;

    private List<OrderDetailResponse> details;
}
