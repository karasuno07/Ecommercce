package com.techstore.ecommerce.object.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AuthenticationInfo {

    private long id;

    private String username;

    @JsonProperty("full_name")
    private String fullName;

    private String image;

    @JsonProperty("role")
    private String roleName;

    private List<String> permissions;
}
