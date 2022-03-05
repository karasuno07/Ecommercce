package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.constant.OrderStatus;
import com.techstore.ecommerce.object.dto.filter.OrderFilter;
import com.techstore.ecommerce.object.dto.mapper.OrderMapper;
import com.techstore.ecommerce.object.dto.request.OrderRequest;
import com.techstore.ecommerce.object.dto.response.OrderResponse;
import com.techstore.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderService service;
    private final OrderMapper mapper;

    @PreAuthorize("hasAuthority('ORDER_READ')")
    @GetMapping
    ResponseEntity<?> getAllOrder(@RequestBody Optional<OrderFilter> filter) {
        Page<OrderResponse> response = service.findAllOrders(
                filter.orElse(new OrderFilter())).map(mapper::toResponseModel);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ORDER_READ') OR hasRole('CUSTOMER')")
    @GetMapping("/{orderId}")
    ResponseEntity<?> getOrderById(@PathVariable int orderId) {
        OrderResponse response = mapper.toResponseModel(service.findOrderById(orderId));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ORDER_CREATE') OR hasRole('CUSTOMER')")
    @PostMapping
    ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequest request) {
        OrderResponse response = mapper.toResponseModel(service.createOrder(request));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ORDER_UPDATE')")
    @PatchMapping("/{orderId}/pending")
    ResponseEntity<?> pendingOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.PENDING);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ORDER_UPDATE')")
    @PatchMapping("/{orderId}/processed")
    ResponseEntity<?> processedOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.PROCESSED);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ORDER_UPDATE')")
    @PatchMapping("/{orderId}/delivering")
    ResponseEntity<?> deliveringOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.DELIVERING);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{orderId}/received")
    ResponseEntity<?> receivedOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.RECEIVED);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{orderId}/declined")
    ResponseEntity<?> declinedOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.DECLINED);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{orderId}")
    ResponseEntity<?> deleteOrder(@PathVariable int orderId) {
        service.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }


}
