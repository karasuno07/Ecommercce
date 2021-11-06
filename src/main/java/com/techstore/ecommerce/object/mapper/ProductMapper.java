package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.entity.Product;
import com.techstore.ecommerce.object.dto.request.ProductRequest;
import com.techstore.ecommerce.object.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = ProductDetailMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    ProductResponse toResponseModel(Product product);

    Product createEntityFromRequest(ProductRequest request);

    void update(@MappingTarget Product product, ProductRequest request);
}
