package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.constant.SuccessMessage;
import com.techstore.ecommerce.object.dto.request.BrandRequest;
import com.techstore.ecommerce.object.dto.response.BrandResponse;
import com.techstore.ecommerce.object.mapper.BrandMapper;
import com.techstore.ecommerce.object.wrapper.AbstractResponse;
import com.techstore.ecommerce.object.wrapper.SuccessResponse;
import com.techstore.ecommerce.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BrandController {
    private final BrandService service;
    private final BrandMapper mapper;

    @PreAuthorize("hasAuthority('BRAND_READ') OR hasRole('CUSTOMER')")
    @GetMapping
    AbstractResponse getAllBrand() {
        List<BrandResponse> response =
                service.findAllBrands().stream()
                       .map(mapper::toResponseModel)
                       .collect(Collectors.toList());

        return new SuccessResponse<>(response, SuccessMessage.FIND_ALL_BRANDS.getMessage());
    }

    @PreAuthorize("hasAuthority('BRAND_READ') OR hasRole('CUSTOMER')")
    @GetMapping("/{brandId}")
    AbstractResponse getBrandById(@PathVariable int brandId) {
        BrandResponse response = mapper.toResponseModel(service.findBrandById(brandId));
        return new SuccessResponse<>(response, SuccessMessage.FIND_BRAND_BY_ID.getMessage() + brandId);
    }

    @PreAuthorize("hasAuthority('BRAND_CREATE')")
    @PostMapping
    AbstractResponse createBrand(@RequestBody @Valid BrandRequest request) {
        BrandResponse response = mapper.toResponseModel(service.createBrand(request));
        return new SuccessResponse<>(
                response, HttpStatus.CREATED.value(), SuccessMessage.CREATE_BRAND.getMessage());
    }

    @PreAuthorize("hasAuthority('BRAND_UPDATE')")
    @PutMapping("/{brandId}")
    AbstractResponse updateBrand(
            @PathVariable int brandId, @RequestBody @Valid BrandRequest request) {
        BrandResponse response = mapper.toResponseModel(service.updateBrand(brandId, request));
        return new SuccessResponse<>(response, SuccessMessage.UPDATE_BRAND.getMessage());
    }

    @PreAuthorize("hasAuthority('BRAND_DELETE')")
    @DeleteMapping("/{brandId}")
    AbstractResponse deleteBrand(@PathVariable long brandId) {
        service.deleteBrand(brandId);
        return new SuccessResponse<>(null, SuccessMessage.DELETE_BRAND.getMessage());
    }
}
