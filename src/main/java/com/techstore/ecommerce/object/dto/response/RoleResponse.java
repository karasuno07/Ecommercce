package com.techstore.ecommerce.object.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class RoleResponse {

    private long id;

    private String name;

    private Set<String> authorities;
}
