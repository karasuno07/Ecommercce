package com.techstore.ecommerce.object.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class Address {

//    @NotBlank(message = "blank")
    private String street;

//    @NotBlank(message = "blank")
    private String ward;

//    @NotBlank(message = "blank")
    private String district;

//    @NotBlank(message = "blank")
    private String city;
}
