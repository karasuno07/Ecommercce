package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.dto.filter.ProductFilter;
import com.techstore.ecommerce.object.dto.mapper.ProductMapper;
import com.techstore.ecommerce.object.dto.mapper.ProductReviewMapper;
import com.techstore.ecommerce.object.dto.request.ProductRequest;
import com.techstore.ecommerce.object.dto.request.ProductReviewRequest;
import com.techstore.ecommerce.object.dto.response.ProductResponse;
import com.techstore.ecommerce.object.dto.response.ProductReviewResponse;
import com.techstore.ecommerce.service.ProductReviewService;
import com.techstore.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    private final ProductReviewService reviewService;
    private final ProductReviewMapper reviewMapper;

    //    @PreAuthorize("hasAuthority('PRODUCT_READ') OR hasRole('CUSTOMER')")
    @GetMapping
    ResponseEntity<?> getAllProduct() {
        List<ProductResponse> response = productService.findAllProducts()
                .stream().map(productMapper::toResponseModel).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

//    @PreAuthorize("hasAuthority('PRODUCT_READ') OR hasRole('CUSTOMER')")
    @GetMapping("/{productId}")
    ResponseEntity<?> getProductById(@PathVariable int productId) {
        ProductResponse response = productMapper.toResponseModel(productService.findProductById(productId));
        return ResponseEntity.ok(response);
    }

//    @PreAuthorize("hasAuthority('PRODUCT_CREATE')")
    @PostMapping
    ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest request) {
        System.out.println(request.getDetails());
        ProductResponse response = productMapper.toResponseModel(productService.createProduct(request));
        return ResponseEntity.ok(response);
    }

//    @PreAuthorize("hasAuthority('PRODUCT_UPDATE')")
    @PutMapping("/{productId}")
    ResponseEntity<?> updateProduct(
            @PathVariable int productId, @RequestBody @Valid ProductRequest request) {
        ProductResponse response = productMapper.toResponseModel(
                productService.updateProduct(productId, request));
        return ResponseEntity.ok(response);
    }

//    @PreAuthorize("hasAuthority('PRODUCT_UPDATE')")
    @PatchMapping("/{productId}/activate")
    ResponseEntity<?> activateProduct(@PathVariable long productId) {
        productService.activateProduct(productId);
        return ResponseEntity.ok().build();
    }

//    @PreAuthorize("hasAuthority('PRODUCT_UPDATE')")
    @PatchMapping("/{productId}/deactivate")
    ResponseEntity<?> deactivateProduct(@PathVariable long productId) {
        productService.deactivateProduct(productId);
        return ResponseEntity.ok().build();
    }

//    @PreAuthorize("hasAuthority('PRODUCT_REVIEW_CREATE') OR hasRole('CUSTOMER')")
    @PostMapping("/review")
    ResponseEntity<?> createProductReview(@RequestBody ProductReviewRequest request) {
        ProductReviewResponse response = reviewMapper.toResponseModel(reviewService.createReview(request));
        return ResponseEntity.ok(response);
    }

//    @PreAuthorize("hasAuthority('PRODUCT_REVIEW_UPDATE') OR hasRole('CUSTOMER')")
    @PutMapping("/review/{reviewId}")
    ResponseEntity<?> updateProductReview(@PathVariable long reviewId,
                                          @RequestBody ProductReviewRequest request) {
        ProductReviewResponse response = reviewMapper.toResponseModel(reviewService.updateReview(reviewId,
                                                                                                 request));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('PRODUCT_REVIEW_DELETE') OR hasRole('CUSTOMER')")
    @DeleteMapping("/review/{reviewId}")
    ResponseEntity<?> deleteProductReview(@PathVariable long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }

}
