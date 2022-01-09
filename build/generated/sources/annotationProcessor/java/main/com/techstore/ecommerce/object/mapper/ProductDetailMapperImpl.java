package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.ProductDetailRequest;
import com.techstore.ecommerce.object.dto.response.ProductDetailResponse;
import com.techstore.ecommerce.object.entity.jpa.ProductDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-09T16:41:34+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class ProductDetailMapperImpl implements ProductDetailMapper {

    @Override
    public ProductDetailResponse toResponseModel(ProductDetail detail) {
        if ( detail == null ) {
            return null;
        }

        ProductDetailResponse productDetailResponse = new ProductDetailResponse();

        productDetailResponse.setInStock( detail.getInStock() );
        productDetailResponse.setPrice( detail.getPrice() );
        productDetailResponse.setDiscount( detail.getDiscount() );
        List<String> list = detail.getImages();
        if ( list != null ) {
            productDetailResponse.setImages( new ArrayList<String>( list ) );
        }

        getDescriptionFromString( productDetailResponse, detail );

        return productDetailResponse;
    }

    @Override
    public ProductDetail createEntityFromRequest(ProductDetailRequest request) {
        if ( request == null ) {
            return null;
        }

        ProductDetail productDetail = new ProductDetail();

        productDetail.setInStock( request.getInStock() );
        productDetail.setPrice( request.getPrice() );
        productDetail.setDiscount( request.getDiscount() );

        getDescriptionFromMap( productDetail, request );

        return productDetail;
    }
}
