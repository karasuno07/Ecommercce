package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.dto.mapper.SupplierMapper;
import com.techstore.ecommerce.object.dto.request.SupplierRequest;
import com.techstore.ecommerce.object.dto.response.SupplierResponse;
import com.techstore.ecommerce.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SupplierController {
    private final SupplierService service;
    private final SupplierMapper mapper;

    @PreAuthorize("hasAuthority('SUPPLIER_READ')")
    @GetMapping
    ResponseEntity<?> getAllSupplier() {
        List<SupplierResponse> response = service.findAllSuppliers().stream()
                                                 .map(mapper::toResponseModel)
                                                 .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('SUPPLIER_READ')")
    @GetMapping("/{supplierId}")
    ResponseEntity<?> getSupplierById(@PathVariable int supplierId) {
        SupplierResponse response = mapper.toResponseModel(service.findSupplierById(supplierId));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('SUPPLIER_CREATE')")
    @PostMapping
    ResponseEntity<?> createSupplier(@RequestBody @Valid SupplierRequest request) {
        SupplierResponse response = mapper.toResponseModel(service.createSupplier(request));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('SUPPLIER_UPDATE')")
    @PutMapping("/{supplierId}")
    ResponseEntity<?> updateSupplier(@PathVariable int supplierId,
                                     @RequestBody @Valid SupplierRequest request) {
        SupplierResponse response = mapper.toResponseModel(service.updateSupplier(supplierId, request));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('SUPPLIER_DELETE')")
    @DeleteMapping("/{supplierId}")
    ResponseEntity<?> deleteSupplier(@PathVariable long supplierId) {
        service.deleteSupplier(supplierId);
        return ResponseEntity.ok().build();
    }
}
