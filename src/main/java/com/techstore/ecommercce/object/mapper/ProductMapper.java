package com.techstore.ecommercce.object.mapper;

import com.techstore.ecommercce.object.entity.Product;
import com.techstore.ecommercce.object.request.ProductRequest;
import com.techstore.ecommercce.object.response.ProductResponse;
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
