package com.techstore.ecommerce.object.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techstore.ecommerce.object.model.Address;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SupplierRequest {

    @NotNull(message = "null")
    private String name;

//    @NotBlank(message = "blank")
    private Address address;

//    @JsonProperty("phone_number")
    @NotBlank(message = "blank")
    private String phoneNumber;

    @NotBlank(message = "blank")
    private String email;
}
