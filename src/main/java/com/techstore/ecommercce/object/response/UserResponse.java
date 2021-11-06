package com.techstore.ecommercce.object.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {

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

    @JsonProperty("role")
    private String roleName;

    private boolean active;
}
