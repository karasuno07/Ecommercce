package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.constant.OrderStatus;
import com.techstore.ecommerce.object.constant.SuccessMessage;
import com.techstore.ecommerce.object.dto.filter.OrderFilter;
import com.techstore.ecommerce.object.dto.request.OrderRequest;
import com.techstore.ecommerce.object.dto.response.OrderResponse;
import com.techstore.ecommerce.object.mapper.OrderMapper;
import com.techstore.ecommerce.object.wrapper.AbstractResponse;
import com.techstore.ecommerce.object.wrapper.SuccessResponse;
import com.techstore.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    AbstractResponse getAllOrder(@RequestBody Optional<OrderFilter> filter) {
        Page<OrderResponse> response = service.findAllOrders(
                filter.orElse(new OrderFilter())).map(mapper::toResponseModel);

        return new SuccessResponse<>(response, SuccessMessage.FIND_ALL_ORDERS.getMessage());
    }

    @PreAuthorize("hasAuthority('ORDER_READ') OR hasRole('CUSTOMER')")
    @GetMapping("/{orderId}")
    AbstractResponse getOrderById(@PathVariable int orderId) {
        OrderResponse response = mapper.toResponseModel(service.findOrderById(orderId));
        return new SuccessResponse<>(
                response, SuccessMessage.FIND_ORDER_BY_ID.getMessage() + orderId);
    }

    @PreAuthorize("hasAuthority('ORDER_CREATE') OR hasRole('CUSTOMER')")
    @PostMapping
    AbstractResponse createOrder(@RequestBody @Valid OrderRequest request) {
        OrderResponse response = mapper.toResponseModel(service.createOrder(request));
        return new SuccessResponse<>(
                response, SuccessMessage.CREATE_ORDER.getMessage());
    }

    @PreAuthorize("hasAuthority('ORDER_UPDATE')")
    @PatchMapping("/{orderId}/pending")
    AbstractResponse pendingOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.PENDING);
        return new SuccessResponse<>(
                null, SuccessMessage.UPDATE_STATUS.getMessage() + OrderStatus.PENDING.getStatus());
    }

    @PreAuthorize("hasAuthority('ORDER_UPDATE')")
    @PatchMapping("/{orderId}/processed")
    AbstractResponse processedOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.PROCESSED);
        return new SuccessResponse<>(
                null, SuccessMessage.UPDATE_STATUS.getMessage() + OrderStatus.PROCESSED.getStatus());
    }

    @PreAuthorize("hasAuthority('ORDER_UPDATE')")
    @PatchMapping("/{orderId}/delivering")
    AbstractResponse deliveringOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.DELIVERING);
        return new SuccessResponse<>(
                null, SuccessMessage.UPDATE_STATUS.getMessage() + OrderStatus.DELIVERING.getStatus());
    }

    @PatchMapping("/{orderId}/received")
    AbstractResponse receivedOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.RECEIVED);
        return new SuccessResponse<>(
                null, SuccessMessage.UPDATE_STATUS.getMessage() + OrderStatus.RECEIVED.getStatus());
    }

    @PatchMapping("/{orderId}/declined")
    AbstractResponse declinedOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.DECLINED);
        return new SuccessResponse<>(
                null, SuccessMessage.UPDATE_STATUS.getMessage() + OrderStatus.DECLINED.getStatus());
    }

    @DeleteMapping("/{orderId}")
    AbstractResponse deleteOrder(@PathVariable int orderId) {
        service.deleteOrder(orderId);
        return new SuccessResponse<>(
                null, SuccessMessage.DELETE_ORDER.getMessage());
    }


}
