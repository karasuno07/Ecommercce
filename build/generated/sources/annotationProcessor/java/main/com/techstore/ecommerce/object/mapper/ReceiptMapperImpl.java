package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.ReceiptDetailRequest;
import com.techstore.ecommerce.object.dto.request.ReceiptRequest;
import com.techstore.ecommerce.object.dto.response.ReceiptResponse;
import com.techstore.ecommerce.object.entity.jpa.Receipt;
import com.techstore.ecommerce.object.entity.jpa.ReceiptDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-09T16:41:36+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class ReceiptMapperImpl implements ReceiptMapper {

    @Override
    public ReceiptResponse toResponseModel(Receipt receipt) {
        if ( receipt == null ) {
            return null;
        }

        ReceiptResponse receiptResponse = new ReceiptResponse();

        receiptResponse.setId( receipt.getId() );
        receiptResponse.setReceiptCode( receipt.getReceiptCode() );

        return receiptResponse;
    }

    @Override
    public Receipt createEntityFromRequest(ReceiptRequest request) {
        if ( request == null ) {
            return null;
        }

        Receipt receipt = new Receipt();

        receipt.setDetails( receiptDetailRequestListToReceiptDetailList( request.getDetails() ) );

        return receipt;
    }

    @Override
    public void update(Receipt receipt, ReceiptRequest request) {
        if ( request == null ) {
            return;
        }

        if ( receipt.getDetails() != null ) {
            List<ReceiptDetail> list = receiptDetailRequestListToReceiptDetailList( request.getDetails() );
            if ( list != null ) {
                receipt.getDetails().clear();
                receipt.getDetails().addAll( list );
            }
        }
        else {
            List<ReceiptDetail> list = receiptDetailRequestListToReceiptDetailList( request.getDetails() );
            if ( list != null ) {
                receipt.setDetails( list );
            }
        }
    }

    protected ReceiptDetail receiptDetailRequestToReceiptDetail(ReceiptDetailRequest receiptDetailRequest) {
        if ( receiptDetailRequest == null ) {
            return null;
        }

        ReceiptDetail receiptDetail = new ReceiptDetail();

        receiptDetail.setQuantity( receiptDetailRequest.getQuantity() );
        receiptDetail.setPrice( receiptDetailRequest.getPrice() );

        return receiptDetail;
    }

    protected List<ReceiptDetail> receiptDetailRequestListToReceiptDetailList(List<ReceiptDetailRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<ReceiptDetail> list1 = new ArrayList<ReceiptDetail>( list.size() );
        for ( ReceiptDetailRequest receiptDetailRequest : list ) {
            list1.add( receiptDetailRequestToReceiptDetail( receiptDetailRequest ) );
        }

        return list1;
    }
}
