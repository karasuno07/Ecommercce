package com.techstore.ecommerce.object.dto.mapper;

import com.techstore.ecommerce.object.entity.jpa.ProductReview;
import com.techstore.ecommerce.object.dto.request.ProductReviewRequest;
import com.techstore.ecommerce.object.dto.response.ProductReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductReviewMapper {

    ProductReviewResponse toResponseModel(ProductReview review);

    ProductReview createEntityFromRequest(ProductReviewRequest request);

    void update(@MappingTarget ProductReview review, ProductReviewRequest request);
}
