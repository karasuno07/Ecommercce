package com.techstore.ecommerce.object.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductReview {

    @NotNull(message = "null")
    @Size(min = 1, max = 5, message = "value of out bound (min: 1, max: 5)")
    private int rating;

    @NotBlank(message = "blank")
    private String comment;
}
