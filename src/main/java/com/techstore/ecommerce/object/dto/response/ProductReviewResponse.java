package com.techstore.ecommerce.object.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ProductReviewResponse {

    private CustomerResponse customer;

    private int rating;

    private String comment;

    @JsonProperty("commented_date")
    private Date commentedDate;
}
