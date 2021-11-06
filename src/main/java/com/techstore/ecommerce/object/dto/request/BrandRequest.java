package com.techstore.ecommerce.object.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BrandRequest {

    @NotBlank(message = "blank")
    private String name;

}
