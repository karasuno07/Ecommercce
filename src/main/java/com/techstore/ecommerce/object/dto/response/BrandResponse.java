package com.techstore.ecommerce.object.dto.response;

import lombok.Data;

@Data
public class BrandResponse {

    private long id;

    private String name;

    private String slug;

    private String image;
}
