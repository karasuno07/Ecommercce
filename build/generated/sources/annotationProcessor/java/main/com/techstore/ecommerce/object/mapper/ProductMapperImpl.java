package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.ProductRequest;
import com.techstore.ecommerce.object.dto.response.BrandResponse;
import com.techstore.ecommerce.object.dto.response.CategoryResponse;
import com.techstore.ecommerce.object.dto.response.ProductDetailResponse;
import com.techstore.ecommerce.object.dto.response.ProductResponse;
import com.techstore.ecommerce.object.dto.response.ProductReviewResponse;
import com.techstore.ecommerce.object.entity.jpa.Brand;
import com.techstore.ecommerce.object.entity.jpa.Category;
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
    date = "2022-02-28T17:51:47+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 11.0.13 (JetBrains s.r.o.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private ProductDetailMapper productDetailMapper;

    @Override
    public ProductResponse toResponseModel(Product product) {
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

    @Override
    public Product createEntityFromRequest(ProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( request.getName() );

        generateSlug( product, request );

        return product;
    }

    @Override
    public void update(Product product, ProductRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            product.setName( request.getName() );
        }

        generateSlug( product, request );
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
}
