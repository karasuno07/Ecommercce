package com.techstore.ecommerce.object.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techstore.ecommerce.object.model.Address;
import com.techstore.ecommerce.object.model.FullName;
import com.techstore.ecommerce.object.validation.NotSupportedImageType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserRequest {

    @NotBlank(message = "blank")
    private String username;

    @NotBlank(message = "blank")
    private String password;

    @JsonProperty("full_name")
    @NotNull(message = "null")
    private FullName fullName;

    @NotNull(message = "null")
    private boolean gender;

    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "null")
    private Date dateOfBirth;

    @JsonProperty("phone_number")
    @NotBlank(message = "blank")
    private String phoneNumber;

    @NotBlank(message = "blank")
    private String email;

    @NotNull(message = "null")
    private Address address;

    //    @NotNull(message = "null")
    @NotSupportedImageType(message = "invalid content type")
    private MultipartFile imageFile;

    @JsonProperty("role_id")
    @NotNull(message = "null")
    private int roleId;
}
