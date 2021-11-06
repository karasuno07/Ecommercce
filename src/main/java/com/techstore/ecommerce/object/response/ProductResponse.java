package com.techstore.ecommerce.object.response;

import com.techstore.ecommerce.object.request.ProductDetailRequest;
import com.techstore.ecommerce.object.model.ProductReview;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {

    private long id;

    private String name;

    private String slug;

    private CategoryResponse category;

    private BrandResponse brand;

    private List<ProductDetailRequest> details;

    private List<ProductReview> reviews;
}
