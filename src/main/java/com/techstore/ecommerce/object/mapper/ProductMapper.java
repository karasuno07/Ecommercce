package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.entity.Product;
import com.techstore.ecommerce.object.request.ProductRequest;
import com.techstore.ecommerce.object.response.ProductResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "details", ignore = true)
    ProductResponse toResponseModel(Product product);

    @Mapping(target = "details", ignore = true)
    Product createEntityFromRequest(ProductRequest request);

    @Mapping(target = "details", ignore = true)
    void update(@MappingTarget Product product, ProductRequest request);

    @AfterMapping
    default void getDescriptionsString(@MappingTarget ProductResponse response, Product product) {
        // TODO: learn how to convert JSON String to Map
    }
}
