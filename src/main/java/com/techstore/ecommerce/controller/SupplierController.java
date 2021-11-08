package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.constant.SuccessMessage;
import com.techstore.ecommerce.object.dto.request.SupplierRequest;
import com.techstore.ecommerce.object.dto.response.SupplierResponse;
import com.techstore.ecommerce.object.mapper.SupplierMapper;
import com.techstore.ecommerce.object.wrapper.AbstractResponse;
import com.techstore.ecommerce.object.wrapper.SuccessResponse;
import com.techstore.ecommerce.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {
  private final SupplierService service;
  private final SupplierMapper mapper;

  @GetMapping
  AbstractResponse getAllSupplier() {
    List<SupplierResponse> response =
        service.findAllSuppliers().stream()
            .map(mapper::toResponseModel)
            .collect(Collectors.toList());

    if (response.isEmpty()) {
      return new SuccessResponse<>(
          HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase());
    }
    return new SuccessResponse<>(response, SuccessMessage.FIND_ALL_SUPPLIER.getMessage());
  }

  @GetMapping("/{supplierId}")
  AbstractResponse getSupplierById(@PathVariable int supplierId) {
    SupplierResponse response = mapper.toResponseModel(service.findSupplierById(supplierId));
    return new SuccessResponse<>(
        response, SuccessMessage.FIND_SUPPLIER_BY_ID.getMessage() + supplierId);
  }

  @PostMapping
  AbstractResponse createSupplier(@RequestBody @Valid SupplierRequest request) {
    SupplierResponse response = mapper.toResponseModel(service.createSupplier(request));
    return new SuccessResponse<>(
        HttpStatus.CREATED.value(), response, SuccessMessage.CREATE_SUPPLIER.getMessage());
  }

  @PutMapping("/{supplierId}")
  AbstractResponse updateSupplier(
      @PathVariable int supplierId, @RequestBody @Valid SupplierRequest request) {
    SupplierResponse response = mapper.toResponseModel(service.updateSupplier(supplierId, request));
    return new SuccessResponse<>(response, SuccessMessage.UPDATE_SUPPLIER.getMessage());
  }

  @DeleteMapping("/{supplierId}")
  AbstractResponse deleteSupplier(@PathVariable long supplierId) {
    service.deleteSupplier(supplierId);
    return new SuccessResponse<>(null, SuccessMessage.DELETE_SUPPLIER.getMessage());
  }
}
