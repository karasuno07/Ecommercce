package com.techstore.ecommercce.object.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Address {

    @NotBlank(message = "blank")
    private String street;

    @NotBlank(message = "blank")
    private String ward;

    @NotBlank(message = "blank")
    private String district;

    @NotBlank(message = "blank")
    private String city;
}
