package com.techstore.ecommerce.object.dto.mapper;

import com.techstore.ecommerce.object.dto.request.BrandRequest;
import com.techstore.ecommerce.object.dto.response.BrandResponse;
import com.techstore.ecommerce.object.entity.jpa.Brand;
import com.techstore.ecommerce.util.CustomStringUtil;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandMapper {

    BrandResponse toResponseModel(Brand brand);

    Brand createEntityFromRequest(BrandRequest request);

    void update(@MappingTarget Brand brand, BrandRequest request);

    @AfterMapping
    default void generateSlug(@MappingTarget Brand brand, BrandRequest request) {
        String slug = CustomStringUtil.generateSlug(request.getName());
        brand.setSlug(slug);
    }
}
