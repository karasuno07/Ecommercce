package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.ReceiptDetailRequest;
import com.techstore.ecommerce.object.dto.response.ReceiptDetailResponse;
import com.techstore.ecommerce.object.entity.jpa.ReceiptDetail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReceiptDetailMapper {

    ReceiptDetailResponse toResponseModel(ReceiptDetail receiptDetail);

    ReceiptDetail createEntityFromRequest(ReceiptDetailRequest request);

    void update(@MappingTarget ReceiptDetail receiptDetail, ReceiptDetailRequest request);

}
