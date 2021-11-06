package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.entity.OrderDetail;
import com.techstore.ecommerce.object.dto.request.OrderDetailRequest;
import com.techstore.ecommerce.object.dto.response.OrderDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderDetailMapper {

    @Mapping(target = "productName", expression = "java(detail.getProduct().getName())")
    @Mapping(target = "productCategory", expression = "java(detail.getProduct().getCategory().getName())")
    @Mapping(target = "productBrand", expression = "java(detail.getProduct().getBrand().getName())")
    OrderDetailResponse toResponseModel(OrderDetail detail);

    OrderDetail createEntityFromRequest(OrderDetailRequest request);

    void update(@MappingTarget OrderDetail detail, OrderDetailRequest request);
}
