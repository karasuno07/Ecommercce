package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.constant.SuccessMessage;
import com.techstore.ecommerce.object.dto.filter.ProductFilter;
import com.techstore.ecommerce.object.dto.request.ProductRequest;
import com.techstore.ecommerce.object.dto.response.ProductResponse;
import com.techstore.ecommerce.object.mapper.ProductMapper;
import com.techstore.ecommerce.object.wrapper.AbstractResponse;
import com.techstore.ecommerce.object.wrapper.SuccessResponse;
import com.techstore.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;

    @GetMapping
    AbstractResponse getAllProduct(@RequestBody Optional<ProductFilter> filter) {
        Page<ProductResponse> response = service.findAllProducts(
                filter.orElse(new ProductFilter())).map(mapper::toResponseModel);

        return new SuccessResponse<>(response, SuccessMessage.FIND_ALL_PRODUCT.getMessage());
    }

    @GetMapping("/{productId}")
    AbstractResponse getProductById(@PathVariable int productId) {
        ProductResponse response = mapper.toResponseModel(service.findProductById(productId));
        return new SuccessResponse<>(
                response, SuccessMessage.FIND_PRODUCT_BY_ID.getMessage() + productId);
    }

    @PostMapping
    AbstractResponse createProduct(@RequestBody @Valid ProductRequest request) {
        ProductResponse response = mapper.toResponseModel(service.createProduct(request));
        return new SuccessResponse<>(
                response, HttpStatus.CREATED.value(), SuccessMessage.CREATE_PRODUCT.getMessage());
    }

    @PutMapping("/{productId}")
    AbstractResponse updateProduct(
            @PathVariable int productId, @RequestBody @Valid ProductRequest request) {
        ProductResponse response = mapper.toResponseModel(service.updateProduct(productId, request));
        return new SuccessResponse<>(response, SuccessMessage.UPDATE_PRODUCT.getMessage());
    }

    @PatchMapping("/{productId}/activate")
    AbstractResponse activateProduct(@PathVariable long productId) {
        service.activateProduct(productId);
        return new SuccessResponse<>(null, SuccessMessage.ACTIVATE_PRODUCT.getMessage());
    }

    @PatchMapping("/{productId}/deactivate")
    AbstractResponse deactivateProduct(@PathVariable long productId) {
        service.deactivateProduct(productId);
        return new SuccessResponse<>(null, SuccessMessage.DEACTIVATE_PRODUCT.getMessage());
    }
}
