package com.techstore.ecommerce.object.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerResponse {

    private long id;

    private String username;

    private String password;

    @JsonProperty("full_name")
    private String fullName;

    private boolean gender;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String email;

    private String address;
    private boolean active;
}
