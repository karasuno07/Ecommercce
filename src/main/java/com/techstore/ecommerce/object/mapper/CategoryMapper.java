package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.CategoryRequest;
import com.techstore.ecommerce.object.dto.response.CategoryResponse;
import com.techstore.ecommerce.object.entity.jpa.Category;
import com.techstore.ecommerce.util.CustomStringUtil;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    CategoryResponse toResponseModel(Category category);

    @Mapping(target = "parent.id", source = "parentId")
    Category createEntityFromRequest(CategoryRequest request);

    void update(@MappingTarget Category category, CategoryRequest request);

    @AfterMapping
    default void generateSlug(@MappingTarget Category category, CategoryRequest request) {
        String slug = CustomStringUtil.generateSlug(request.getName());
        category.setSlug(slug);
    }
}
