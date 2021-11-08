package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.constant.SuccessMessage;
import com.techstore.ecommerce.object.dto.filter.CustomerFilter;
import com.techstore.ecommerce.object.dto.request.CustomerRequest;
import com.techstore.ecommerce.object.dto.response.CustomerResponse;
import com.techstore.ecommerce.object.entity.jpa.Customer;
import com.techstore.ecommerce.object.mapper.CustomerMapper;
import com.techstore.ecommerce.object.model.AuthenticationInfo;
import com.techstore.ecommerce.object.wrapper.AbstractResponse;
import com.techstore.ecommerce.object.wrapper.SuccessResponse;
import com.techstore.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService service;
  private final CustomerMapper mapper;

  @GetMapping
  AbstractResponse getAllCustomer(@RequestBody Optional<CustomerFilter> filter) {
    Page<CustomerResponse> response =
        service.findAllCustomers(filter.orElse(new CustomerFilter())).map(mapper::toResponseModel);

    if (response.getContent().isEmpty()) {
      return new SuccessResponse<>(
          HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase());
    }

    return new SuccessResponse<>(response, SuccessMessage.FIND_ALL_USER.getMessage());
  }

  @GetMapping("/{customerId}")
  AbstractResponse getCustomerById(@PathVariable int customerId) {
    CustomerResponse response = mapper.toResponseModel(service.findCustomerById(customerId));
    return new SuccessResponse<>(response, SuccessMessage.FIND_USER_BY_ID.getMessage() + customerId);
  }

  @PostMapping
  AbstractResponse createCustomer(@RequestBody @Valid CustomerRequest request) {
    CustomerResponse response = mapper.toResponseModel(service.createCustomer(request));
    return new SuccessResponse<>(
        HttpStatus.CREATED.value(), response, SuccessMessage.CREATE_USER.getMessage());
  }

  @PutMapping("/{customerId}")
  AbstractResponse updateCustomer(@PathVariable int customerId, @RequestBody @Valid CustomerRequest request) {
    CustomerResponse response = mapper.toResponseModel(service.updateCustomer(customerId, request));
    return new SuccessResponse<>(response, SuccessMessage.UPDATE_USER.getMessage());
  }

  @PatchMapping("/{customerId}/activate")
  AbstractResponse activateCustomer(@PathVariable long customerId) {
    service.activateCustomer(customerId);
    return new SuccessResponse<>(null, SuccessMessage.ACTIVATE_USER.getMessage());
  }

  @PatchMapping("/{customerId}/deactivate")
  AbstractResponse deactivateCustomer(@PathVariable long customerId) {
    service.deactivateCustomer(customerId);
    return new SuccessResponse<>(null, SuccessMessage.DEACTIVATE_USER.getMessage());
  }
}
