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

    @JsonProperty("is_default")
    private boolean isDefault;

    @JsonProperty("product_id")
//    @NotNull(message = "null")
    private int productId;

    @JsonProperty("in_stock")
    @NotNull(message = "null")
    private int inStock;

    @NotNull(message = "null")
    private BigDecimal price;

    @NotNull(message = "null")
    private BigDecimal discount;

    @NotEmpty(message = "empty")
    private ProductProperty descriptions;

    @NotEmpty(message = "empty")
    private List<MultipartFile> imageFiles;
}
