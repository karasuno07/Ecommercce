package com.techstore.ecommerce.object.response;

import lombok.Data;

@Data
public class CategoryResponse {

    private long id;

    private String name;

    private String slug;

    private CategoryResponse parent;
}
