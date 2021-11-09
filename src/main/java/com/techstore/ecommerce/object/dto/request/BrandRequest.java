package com.techstore.ecommerce.object.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BrandRequest {

    @NotBlank(message = "blank")
    private String name;

    @NotNull(message = "null")

    private MultipartFile imageFile;

}
