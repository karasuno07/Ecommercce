package com.techstore.ecommercce.object.response;

import lombok.Data;

@Data
public class CategoryResponse {

    private long id;

    private String name;

    private String slug;

    private CategoryResponse parent;
}
