package com.techstore.ecommerce.object.dto.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techstore.ecommerce.object.model.Pagination;
import lombok.Data;

import java.util.Date;

@Data
public class UserFilter {

    private String username;

    @JsonProperty("full_name")
    private String fullName;

    private Boolean gender;

    @JsonProperty("dob_from")
    private Date dobFrom;

    @JsonProperty("dob_to")
    private Date dobTo;

    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    private Boolean active;

    private Pagination pagination = new Pagination(10);
}
