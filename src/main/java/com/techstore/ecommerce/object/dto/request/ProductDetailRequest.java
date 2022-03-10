package com.techstore.ecommerce.object.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techstore.ecommerce.object.model.ProductProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDetailRequest {

    private boolean isDefault;

    private int productId;

    @NotNull(message = "null")
    private int inStock;

    @NotNull(message = "null")
    private BigDecimal price;

    @NotNull(message = "null")
    private BigDecimal discount;

    @NotEmpty(message = "empty")
    private ProductProperty descriptions;

    private List<MultipartFile> imageFiles;
}
