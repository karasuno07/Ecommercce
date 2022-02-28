package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.OrderDetailRequest;
import com.techstore.ecommerce.object.dto.request.OrderRequest;
import com.techstore.ecommerce.object.dto.response.OrderDetailResponse;
import com.techstore.ecommerce.object.dto.response.OrderResponse;
import com.techstore.ecommerce.object.entity.jpa.Order;
import com.techstore.ecommerce.object.entity.jpa.OrderDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-28T17:51:47+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 11.0.13 (JetBrains s.r.o.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public OrderResponse toResponseModel(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId( order.getId() );
        orderResponse.setRecipientName( order.getRecipientName() );
        orderResponse.setPhoneNumber( order.getPhoneNumber() );
        orderResponse.setEmail( order.getEmail() );
        orderResponse.setAddress( order.getAddress() );
        orderResponse.setLastShippingDate( order.getLastShippingDate() );
        orderResponse.setLastReceiveDate( order.getLastReceiveDate() );
        orderResponse.setDetails( orderDetailListToOrderDetailResponseList( order.getDetails() ) );

        return orderResponse;
    }

    @Override
    public Order createEntityFromRequest(OrderRequest request) {
        if ( request == null ) {
            return null;
        }

        Order order = new Order();

        order.setPhoneNumber( request.getPhoneNumber() );
        order.setEmail( request.getEmail() );
        order.setDetails( orderDetailRequestListToOrderDetailList( request.getDetails() ) );

        getPropertiesFromModel( order, request );

        return order;
    }

    @Override
    public void update(Order order, OrderRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getPhoneNumber() != null ) {
            order.setPhoneNumber( request.getPhoneNumber() );
        }
        if ( request.getEmail() != null ) {
            order.setEmail( request.getEmail() );
        }
        if ( order.getDetails() != null ) {
            List<OrderDetail> list = orderDetailRequestListToOrderDetailList( request.getDetails() );
            if ( list != null ) {
                order.getDetails().clear();
                order.getDetails().addAll( list );
            }
        }
        else {
            List<OrderDetail> list = orderDetailRequestListToOrderDetailList( request.getDetails() );
            if ( list != null ) {
                order.setDetails( list );
            }
        }

        getPropertiesFromModel( order, request );
    }

    protected List<OrderDetailResponse> orderDetailListToOrderDetailResponseList(List<OrderDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDetailResponse> list1 = new ArrayList<OrderDetailResponse>( list.size() );
        for ( OrderDetail orderDetail : list ) {
            list1.add( orderDetailMapper.toResponseModel( orderDetail ) );
        }

        return list1;
    }

    protected List<OrderDetail> orderDetailRequestListToOrderDetailList(List<OrderDetailRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDetail> list1 = new ArrayList<OrderDetail>( list.size() );
        for ( OrderDetailRequest orderDetailRequest : list ) {
            list1.add( orderDetailMapper.createEntityFromRequest( orderDetailRequest ) );
        }

        return list1;
    }
}
