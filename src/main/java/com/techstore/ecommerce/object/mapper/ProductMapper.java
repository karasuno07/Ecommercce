package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.ProductRequest;
import com.techstore.ecommerce.object.dto.response.ProductResponse;
import com.techstore.ecommerce.object.entity.jpa.Product;
import com.techstore.ecommerce.util.CustomStringUtil;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = ProductDetailMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    ProductResponse toResponseModel(Product product);

    @Mapping(target = "details", ignore = true)
        // Phải ignore nó để xử lý thủ công ở service layer
    Product createEntityFromRequest(ProductRequest request);

    @Mapping(target = "details", ignore = true)
        // Phải ignore nó để xử lý thủ công ở service layer
    void update(@MappingTarget Product product, ProductRequest request);

    @AfterMapping
    default void generateSlug(@MappingTarget Product product, ProductRequest request) {
        String slug = CustomStringUtil.generateSlug(request.getName());
        product.setSlug(slug);
    }
}
