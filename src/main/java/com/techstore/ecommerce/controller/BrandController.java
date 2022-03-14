package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.dto.mapper.BrandMapper;
import com.techstore.ecommerce.object.dto.request.BrandRequest;
import com.techstore.ecommerce.object.dto.response.BrandResponse;
import com.techstore.ecommerce.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    //@PreAuthorize("hasAuthority('BRAND_READ') OR hasRole('CUSTOMER')")
    @GetMapping
    ResponseEntity<?> getAllBrand() {
        List<BrandResponse> response = service.findAllBrands().stream()
                                              .map(mapper::toResponseModel)
                                              .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    //@PreAuthorize("hasAuthority('BRAND_READ') OR hasRole('CUSTOMER')")
    @GetMapping("/{brandId}")
    ResponseEntity<?> getBrandById(@PathVariable int brandId) {
        BrandResponse response = mapper.toResponseModel(service.findBrandById(brandId));
        return ResponseEntity.ok(response);
    }

    //@PreAuthorize("hasAuthority('BRAND_CREATE')")
    @PostMapping()
    ResponseEntity<?> createBrand(@ModelAttribute @Valid BrandRequest request) {
        System.out.println(request);
        BrandResponse response = mapper.toResponseModel(service.createBrand(request));
        return ResponseEntity.ok(response);
    }

    //@PreAuthorize("hasAuthority('BRAND_UPDATE')")
    @PutMapping("/{brandId}")
    ResponseEntity<?> updateBrand(
            @PathVariable int brandId, @ModelAttribute @Valid BrandRequest request) {
        BrandResponse response = mapper.toResponseModel(service.updateBrand(brandId, request));
        return ResponseEntity.ok(response);
    }

    //@PreAuthorize("hasAuthority('BRAND_DELETE')")
    @DeleteMapping("/{brandId}")
    ResponseEntity<?> deleteBrand(@PathVariable long brandId) {
        service.deleteBrand(brandId);
        return ResponseEntity.ok().build();
    }
}
