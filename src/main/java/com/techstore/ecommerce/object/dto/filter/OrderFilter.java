package com.techstore.ecommerce.object.dto.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techstore.ecommerce.object.model.Pagination;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderFilter {
    @JsonFilter("order_code")
    private String orderCode;

    @JsonFilter("date_to")
    private Date dateTo;

    @JsonFilter("date_form")
    private Date dateForm;

    @JsonProperty("price_from")
    private BigDecimal priceFrom;

    @JsonProperty("price_to")
    private BigDecimal priceTo;

    private Pagination pagination = new Pagination(10);
}
