package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.entity.jpa.Order;
import com.techstore.ecommerce.object.model.Address;
import com.techstore.ecommerce.object.model.FullName;
import com.techstore.ecommerce.object.dto.request.OrderRequest;
import com.techstore.ecommerce.object.dto.response.OrderResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = OrderDetailMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    OrderResponse toResponseModel(Order order);

    @Mapping(target = "recipientName", ignore = true)
    @Mapping(target = "address", ignore = true)
    Order createEntityFromRequest(OrderRequest request);

    @Mapping(target = "recipientName", ignore = true)
    @Mapping(target = "address", ignore = true)
    void update(@MappingTarget Order order, OrderRequest request);

    @AfterMapping
    default void getPropertiesFromModel(@MappingTarget Order order, OrderRequest request) {
        FullName fullName = request.getRecipientName();
        String recipientName = String.format("%s %s", fullName.getFirstName(), fullName.getLastName());
        order.setRecipientName(recipientName);

        Address address = request.getAddress();
        String recipientAddress = String.format("%s, %s, %s, %s",
                                                address.getStreet(), address.getWard(),
                                                address.getDistrict(), address.getCity());
        order.setAddress(recipientAddress);
    }
}
