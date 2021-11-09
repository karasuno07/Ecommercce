package com.techstore.ecommerce.object.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductReviewRequest {

    @NotNull(message = "null")
    @Size(min = 1, max = 5, message = "value of out bound (min: 1, max: 5)")
    private int rating;

    @NotBlank(message = "blank")
    private String comment;

    @JsonProperty("user_id")
    @NotNull(message = "null")
    private int userId;

    @JsonProperty("product_id")
    @NotNull(message = "null")
    private int productId;
}
