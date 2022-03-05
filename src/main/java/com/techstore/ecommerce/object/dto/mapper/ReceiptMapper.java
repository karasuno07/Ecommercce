package com.techstore.ecommerce.object.dto.mapper;

import com.techstore.ecommerce.object.dto.request.ReceiptRequest;
import com.techstore.ecommerce.object.dto.response.ReceiptResponse;
import com.techstore.ecommerce.object.entity.jpa.Receipt;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReceiptMapper {

    ReceiptResponse toResponseModel(Receipt receipt);

    Receipt createEntityFromRequest(ReceiptRequest request);

    void update(@MappingTarget Receipt receipt, ReceiptRequest request);

}
