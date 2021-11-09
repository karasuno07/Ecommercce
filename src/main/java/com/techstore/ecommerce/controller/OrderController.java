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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final OrderMapper mapper;

    @GetMapping
    AbstractResponse getAllOrder(@RequestBody Optional<OrderFilter> filter) {
        Page<OrderResponse> response = service.findAllOrders(
                filter.orElse(new OrderFilter())).map(mapper::toResponseModel);

        return new SuccessResponse<>(response, SuccessMessage.FIND_ALL_ORDERS.getMessage());
    }

    @GetMapping("/{orderId}")
    AbstractResponse getOrderById(@PathVariable int orderId) {
        OrderResponse response = mapper.toResponseModel(service.findOrderById(orderId));
        return new SuccessResponse<>(
                response, SuccessMessage.FIND_ORDER_BY_ID.getMessage() + orderId);
    }

    @PostMapping
    AbstractResponse createOrder(@RequestBody @Valid OrderRequest request) {
        OrderResponse response = mapper.toResponseModel(service.createOrder(request));
        return new SuccessResponse<>(
                response, HttpStatus.CREATED.value(), SuccessMessage.CREATE_ORDER.getMessage());
    }

    @PatchMapping("/{orderId}/pending")
    AbstractResponse pendingOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.PENDING);
    return new SuccessResponse<>(
        null, SuccessMessage.UPDATE_STATUS.getMessage()+OrderStatus.PENDING.getStatus());
    }

    @PatchMapping("/{orderId}/processed")
    AbstractResponse processedOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.PROCESSED);
        return new SuccessResponse<>(
                null, SuccessMessage.UPDATE_STATUS.getMessage()+OrderStatus.PROCESSED.getStatus());
    }

    @PatchMapping("/{orderId}/delivering")
    AbstractResponse deliveringOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.DELIVERING);
        return new SuccessResponse<>(
                null, SuccessMessage.UPDATE_STATUS.getMessage()+OrderStatus.DELIVERING.getStatus());
    }

    @PatchMapping("/{orderId}/received")
    AbstractResponse receivedOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.RECEIVED);
        return new SuccessResponse<>(
                null, SuccessMessage.UPDATE_STATUS.getMessage()+OrderStatus.RECEIVED.getStatus());
    }

    @PatchMapping("/{orderId}/declined")
    AbstractResponse declinedOrderStatus(@PathVariable long orderId) {
        service.deliveryOrder(orderId, OrderStatus.DECLINED);
        return new SuccessResponse<>(
                null, SuccessMessage.UPDATE_STATUS.getMessage()+OrderStatus.DECLINED.getStatus());
    }

    @DeleteMapping("/{orderId}")
    AbstractResponse deleteOrder(@PathVariable int orderId) {
        service.deleteOrder(orderId);
        return new SuccessResponse<>(
                null, SuccessMessage.DELETE_ORDER.getMessage());
    }


}
