package com.techstore.ecommerce.service;

import com.techstore.ecommerce.object.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.dto.request.ProductReviewRequest;
import com.techstore.ecommerce.object.entity.jpa.Product;
import com.techstore.ecommerce.object.entity.jpa.ProductReview;
import com.techstore.ecommerce.object.entity.jpa.User;
import com.techstore.ecommerce.object.dto.mapper.ProductReviewMapper;
import com.techstore.ecommerce.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductReviewService {

    private final ProductReviewRepository reviewRepo;
    private final ProductReviewMapper reviewMapper;

    private final ProductService productService;
    private final UserService userService;

    public ProductReview findReviewById(long id) {
        return reviewRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found review with id " + id));
    }

    public ProductReview createReview(ProductReviewRequest request) {
        ProductReview review = reviewMapper.createEntityFromRequest(request);

        Product product = productService.findProductById(request.getProductId());
        review.setProduct(product);
        User user = userService.findUserById(request.getUserId());
        review.setUser(user);

        return reviewRepo.save(review);
    }

    public ProductReview updateReview(long reviewId, ProductReviewRequest request) {
        ProductReview review = findReviewById(reviewId);
        reviewMapper.update(review, request);

        Product product = productService.findProductById(request.getProductId());
        review.setProduct(product);
        User user = userService.findUserById(request.getUserId());
        review.setUser(user);

        return reviewRepo.save(review);
    }

    public void deleteReview(long reviewId) {
        ProductReview review = findReviewById(reviewId);
        reviewRepo.delete(review);
    }
}
