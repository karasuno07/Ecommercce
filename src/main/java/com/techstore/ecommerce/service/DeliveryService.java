package com.techstore.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final OrderService orderService;

    // Khi đơn hàng được bên giao hàng tiếp nhận =>
    // set trạng thái đơn hàng thành đang giao
}
