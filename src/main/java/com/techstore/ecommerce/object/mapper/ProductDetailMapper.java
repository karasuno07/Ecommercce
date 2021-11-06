package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.entity.ProductDetail;
import com.techstore.ecommerce.object.dto.request.ProductDetailRequest;
import com.techstore.ecommerce.object.dto.response.ProductDetailResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductDetailMapper {

    @Mapping(target = "descriptions", ignore = true)
    ProductDetailResponse toResponseModel(ProductDetail detail);

    @Mapping(target = "descriptions", ignore = true)
    @Mapping(target = "images", ignore = true)
    ProductDetail createEntityFromRequest(ProductDetailRequest request);

    @AfterMapping
    default void getDescriptionFromString(@MappingTarget ProductDetailResponse response,
                                          ProductDetail detail) {
        // TODO: Using Jackson to convert JSON String to Map
    }

    @AfterMapping
    default void getDescriptionFromMap(@MappingTarget ProductDetail detail,
                                       ProductDetailRequest request) {
        // TODO: Using Jackson to convert Map to JSON String
    }
}
