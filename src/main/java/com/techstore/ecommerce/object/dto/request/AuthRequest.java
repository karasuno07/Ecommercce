package com.techstore.ecommerce.object.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthRequest {

    @NotBlank(message = "blank")
    private String username;

    @NotBlank(message = "blank")
    private String password;
}
