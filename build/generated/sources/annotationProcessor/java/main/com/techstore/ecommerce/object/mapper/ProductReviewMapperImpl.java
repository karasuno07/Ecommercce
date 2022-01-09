package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.ProductReviewRequest;
import com.techstore.ecommerce.object.dto.response.ProductReviewResponse;
import com.techstore.ecommerce.object.entity.jpa.ProductReview;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-09T20:10:05+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class ProductReviewMapperImpl implements ProductReviewMapper {

    @Override
    public ProductReviewResponse toResponseModel(ProductReview review) {
        if ( review == null ) {
            return null;
        }

        ProductReviewResponse productReviewResponse = new ProductReviewResponse();

        productReviewResponse.setRating( review.getRating() );
        productReviewResponse.setComment( review.getComment() );
        productReviewResponse.setCommentedDate( review.getCommentedDate() );

        return productReviewResponse;
    }

    @Override
    public ProductReview createEntityFromRequest(ProductReviewRequest request) {
        if ( request == null ) {
            return null;
        }

        ProductReview productReview = new ProductReview();

        productReview.setRating( request.getRating() );
        productReview.setComment( request.getComment() );

        return productReview;
    }

    @Override
    public void update(ProductReview review, ProductReviewRequest request) {
        if ( request == null ) {
            return;
        }

        review.setRating( request.getRating() );
        if ( request.getComment() != null ) {
            review.setComment( request.getComment() );
        }
    }
}
