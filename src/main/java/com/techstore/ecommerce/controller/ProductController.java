package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.constant.SuccessMessage;
import com.techstore.ecommerce.object.dto.filter.ProductFilter;
import com.techstore.ecommerce.object.dto.request.ProductRequest;
import com.techstore.ecommerce.object.dto.request.ProductReviewRequest;
import com.techstore.ecommerce.object.dto.response.ProductResponse;
import com.techstore.ecommerce.object.dto.response.ProductReviewResponse;
import com.techstore.ecommerce.object.mapper.ProductMapper;
import com.techstore.ecommerce.object.mapper.ProductReviewMapper;
import com.techstore.ecommerce.object.wrapper.AbstractResponse;
import com.techstore.ecommerce.object.wrapper.SuccessResponse;
import com.techstore.ecommerce.service.ProductReviewService;
import com.techstore.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    private final ProductReviewService reviewService;
    private final ProductReviewMapper reviewMapper;

    @PreAuthorize("hasAuthority('PRODUCT_READ') OR hasRole('CUSTOMER')")
    @GetMapping
    AbstractResponse getAllProduct(@RequestBody Optional<ProductFilter> filter) {
        Page<ProductResponse> response = productService.findAllProducts(
                filter.orElse(new ProductFilter())).map(productMapper::toResponseModel);

        return new SuccessResponse<>(response, SuccessMessage.FIND_ALL_PRODUCT.getMessage());
    }

    @PreAuthorize("hasAuthority('PRODUCT_READ') OR hasRole('CUSTOMER')")
    @GetMapping("/{productId}")
    AbstractResponse getProductById(@PathVariable int productId) {
        ProductResponse response = productMapper.toResponseModel(productService.findProductById(productId));
        return new SuccessResponse<>(
                response, SuccessMessage.FIND_PRODUCT_BY_ID.getMessage() + productId);
    }

    @PreAuthorize("hasAuthority('PRODUCT_CREATE')")
    @PostMapping
    AbstractResponse createProduct(@RequestBody @Valid ProductRequest request) {
        ProductResponse response = productMapper.toResponseModel(productService.createProduct(request));
        return new SuccessResponse<>(
                response, HttpStatus.CREATED.value(), SuccessMessage.CREATE_PRODUCT.getMessage());
    }

    @PreAuthorize("hasAuthority('PRODUCT_UPDATE')")
    @PutMapping("/{productId}")
    AbstractResponse updateProduct(
            @PathVariable int productId, @RequestBody @Valid ProductRequest request) {
        ProductResponse response = productMapper.toResponseModel(
                productService.updateProduct(productId, request));
        return new SuccessResponse<>(response, SuccessMessage.UPDATE_PRODUCT.getMessage());
    }

    @PreAuthorize("hasAuthority('PRODUCT_UPDATE')")
    @PatchMapping("/{productId}/activate")
    AbstractResponse activateProduct(@PathVariable long productId) {
        productService.activateProduct(productId);
        return new SuccessResponse<>(null, SuccessMessage.ACTIVATE_PRODUCT.getMessage());
    }

    @PreAuthorize("hasAuthority('PRODUCT_UPDATE')")
    @PatchMapping("/{productId}/deactivate")
    AbstractResponse deactivateProduct(@PathVariable long productId) {
        productService.deactivateProduct(productId);
        return new SuccessResponse<>(null, SuccessMessage.DEACTIVATE_PRODUCT.getMessage());
    }

    @PreAuthorize("hasAuthority('PRODUCT_REVIEW_CREATE') OR hasRole('CUSTOMER')")
    @PostMapping("/review")
    AbstractResponse createProductReview(@RequestBody ProductReviewRequest request) {
        ProductReviewResponse response =
                reviewMapper.toResponseModel(reviewService.createReview(request));
        return new SuccessResponse<>(response, null);
    }

    @PreAuthorize("hasAuthority('PRODUCT_REVIEW_UPDATE') OR hasRole('CUSTOMER')")
    @PutMapping("/review/{reviewId}")
    AbstractResponse updateProductReview(@PathVariable long reviewId,
                                         @RequestBody ProductReviewRequest request) {
        ProductReviewResponse response =
                reviewMapper.toResponseModel(reviewService.updateReview(reviewId, request));
        return new SuccessResponse<>(response, null);
    }

    @PreAuthorize("hasAuthority('PRODUCT_REVIEW_DELETE') OR hasRole('CUSTOMER')")
    @DeleteMapping("/review/{reviewId}")
    AbstractResponse deleteProductReview(@PathVariable long reviewId) {
        reviewService.deleteReview(reviewId);
        return new SuccessResponse<>(true, null);
    }

}
