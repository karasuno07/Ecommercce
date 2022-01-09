package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.ReceiptDetailRequest;
import com.techstore.ecommerce.object.dto.response.ReceiptDetailResponse;
import com.techstore.ecommerce.object.entity.jpa.ReceiptDetail;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-09T16:41:35+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class ReceiptDetailMapperImpl implements ReceiptDetailMapper {

    @Override
    public ReceiptDetailResponse toResponseModel(ReceiptDetail receiptDetail) {
        if ( receiptDetail == null ) {
            return null;
        }

        ReceiptDetailResponse receiptDetailResponse = new ReceiptDetailResponse();

        receiptDetailResponse.setId( receiptDetail.getId() );
        receiptDetailResponse.setQuantity( receiptDetail.getQuantity() );
        receiptDetailResponse.setPrice( receiptDetail.getPrice() );

        return receiptDetailResponse;
    }

    @Override
    public ReceiptDetail createEntityFromRequest(ReceiptDetailRequest request) {
        if ( request == null ) {
            return null;
        }

        ReceiptDetail receiptDetail = new ReceiptDetail();

        receiptDetail.setQuantity( request.getQuantity() );
        receiptDetail.setPrice( request.getPrice() );

        return receiptDetail;
    }

    @Override
    public void update(ReceiptDetail receiptDetail, ReceiptDetailRequest request) {
        if ( request == null ) {
            return;
        }

        receiptDetail.setQuantity( request.getQuantity() );
        if ( request.getPrice() != null ) {
            receiptDetail.setPrice( request.getPrice() );
        }
    }
}
