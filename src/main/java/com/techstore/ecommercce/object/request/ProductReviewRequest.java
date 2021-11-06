package com.techstore.ecommercce.object.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techstore.ecommercce.object.model.ProductReview;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductReviewRequest extends ProductReview {

    @JsonProperty("customer_id")
    @NotNull(message = "null")
    private int customerId;

    @JsonProperty("product_id")
    @NotNull(message = "null")
    private int productId;
}
