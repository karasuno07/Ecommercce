package com.techstore.ecommercce.object.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FullName {

    @JsonProperty("first_name")
    @NotBlank(message = "blank")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "blank")
    private String lastName;
}
