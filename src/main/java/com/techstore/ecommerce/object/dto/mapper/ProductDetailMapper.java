package com.techstore.ecommerce.object.dto.mapper;

import com.techstore.ecommerce.object.entity.jpa.ProductDetail;
import com.techstore.ecommerce.object.dto.request.ProductDetailRequest;
import com.techstore.ecommerce.object.dto.response.ProductDetailResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDetailMapper {

    ProductDetailResponse toResponseModel(ProductDetail detail);

    ProductDetail createEntityFromRequest(ProductDetailRequest request);
}
