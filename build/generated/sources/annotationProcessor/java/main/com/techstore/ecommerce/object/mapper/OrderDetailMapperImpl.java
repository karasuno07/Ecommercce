package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.OrderDetailRequest;
import com.techstore.ecommerce.object.dto.response.BrandResponse;
import com.techstore.ecommerce.object.dto.response.CategoryResponse;
import com.techstore.ecommerce.object.dto.response.OrderDetailResponse;
import com.techstore.ecommerce.object.dto.response.ProductDetailResponse;
import com.techstore.ecommerce.object.dto.response.ProductResponse;
import com.techstore.ecommerce.object.dto.response.ProductReviewResponse;
import com.techstore.ecommerce.object.entity.jpa.Brand;
import com.techstore.ecommerce.object.entity.jpa.Category;
import com.techstore.ecommerce.object.entity.jpa.OrderDetail;
import com.techstore.ecommerce.object.entity.jpa.Product;
import com.techstore.ecommerce.object.entity.jpa.ProductDetail;
import com.techstore.ecommerce.object.entity.jpa.ProductReview;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-09T20:10:05+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class OrderDetailMapperImpl implements OrderDetailMapper {

    @Autowired
    private ProductDetailMapper productDetailMapper;

    @Override
    public OrderDetailResponse toResponseModel(OrderDetail detail) {
        if ( detail == null ) {
            return null;
        }

        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();

        orderDetailResponse.setId( detail.getId() );
        orderDetailResponse.setProduct( productToProductResponse( detail.getProduct() ) );
        orderDetailResponse.setQuantity( detail.getQuantity() );
        orderDetailResponse.setPrice( detail.getPrice() );

        return orderDetailResponse;
    }

    @Override
    public OrderDetail createEntityFromRequest(OrderDetailRequest request) {
        if ( request == null ) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setQuantity( request.getQuantity() );
        orderDetail.setPrice( request.getPrice() );

        return orderDetail;
    }

    @Override
    public void update(OrderDetail detail, OrderDetailRequest request) {
        if ( request == null ) {
            return;
        }

        detail.setQuantity( request.getQuantity() );
        if ( request.getPrice() != null ) {
            detail.setPrice( request.getPrice() );
        }
    }

    protected CategoryResponse categoryToCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( category.getId() );
        categoryResponse.setName( category.getName() );
        categoryResponse.setSlug( category.getSlug() );
        categoryResponse.setParent( categoryToCategoryResponse( category.getParent() ) );

        return categoryResponse;
    }

    protected BrandResponse brandToBrandResponse(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandResponse brandResponse = new BrandResponse();

        brandResponse.setId( brand.getId() );
        brandResponse.setName( brand.getName() );
        brandResponse.setSlug( brand.getSlug() );

        return brandResponse;
    }

    protected List<ProductDetailResponse> productDetailListToProductDetailResponseList(List<ProductDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDetailResponse> list1 = new ArrayList<ProductDetailResponse>( list.size() );
        for ( ProductDetail productDetail : list ) {
            list1.add( productDetailMapper.toResponseModel( productDetail ) );
        }

        return list1;
    }

    protected ProductReviewResponse productReviewToProductReviewResponse(ProductReview productReview) {
        if ( productReview == null ) {
            return null;
        }

        ProductReviewResponse productReviewResponse = new ProductReviewResponse();

        productReviewResponse.setRating( productReview.getRating() );
        productReviewResponse.setComment( productReview.getComment() );
        productReviewResponse.setCommentedDate( productReview.getCommentedDate() );

        return productReviewResponse;
    }

    protected List<ProductReviewResponse> productReviewListToProductReviewResponseList(List<ProductReview> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductReviewResponse> list1 = new ArrayList<ProductReviewResponse>( list.size() );
        for ( ProductReview productReview : list ) {
            list1.add( productReviewToProductReviewResponse( productReview ) );
        }

        return list1;
    }

    protected ProductResponse productToProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId( product.getId() );
        productResponse.setName( product.getName() );
        productResponse.setSlug( product.getSlug() );
        productResponse.setCategory( categoryToCategoryResponse( product.getCategory() ) );
        productResponse.setBrand( brandToBrandResponse( product.getBrand() ) );
        productResponse.setDetails( productDetailListToProductDetailResponseList( product.getDetails() ) );
        productResponse.setReviews( productReviewListToProductReviewResponseList( product.getReviews() ) );
        productResponse.setAvailable( product.isAvailable() );

        return productResponse;
    }
}
