package com.techstore.ecommerce.object.dto.mapper;

import com.techstore.ecommerce.object.entity.jpa.OrderDetail;
import com.techstore.ecommerce.object.dto.request.OrderDetailRequest;
import com.techstore.ecommerce.object.dto.response.OrderDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = ProductDetailMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderDetailMapper {

    OrderDetailResponse toResponseModel(OrderDetail detail);

    OrderDetail createEntityFromRequest(OrderDetailRequest request);

    void update(@MappingTarget OrderDetail detail, OrderDetailRequest request);
}
