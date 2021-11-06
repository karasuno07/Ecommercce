package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.entity.ProductReview;
import com.techstore.ecommerce.object.dto.request.ProductReviewRequest;
import com.techstore.ecommerce.object.dto.response.ProductReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductReviewMapper {

    ProductReviewResponse toResponseModel(ProductReview review);

    ProductReview createEntityFromRequest(ProductReviewRequest request);

    void update(@MappingTarget ProductReview review, ProductReviewRequest request);
}
