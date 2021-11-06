package com.techstore.ecommercce.object.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class RoleRequest {

    @NotBlank(message = "blank")
    private String name;

    @NotEmpty(message = "empty")
    // TODO: create custom validation for authority enum
    private Set<String> authorities;
}
