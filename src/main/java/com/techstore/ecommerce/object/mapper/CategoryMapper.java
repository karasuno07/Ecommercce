package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.CategoryRequest;
import com.techstore.ecommerce.object.dto.response.CategoryResponse;
import com.techstore.ecommerce.object.entity.jpa.Category;
import com.techstore.ecommerce.util.CustomStringUtil;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    CategoryResponse toResponseModel(Category category);

    Category createEntityFromRequest(CategoryRequest request);

    void update(@MappingTarget Category category, CategoryRequest request);

    @AfterMapping
    default void generateSlug(@MappingTarget Category category, CategoryRequest request) {
        String slug = CustomStringUtil.generateSlug(request.getName());
        category.setSlug(slug);
    }
}
