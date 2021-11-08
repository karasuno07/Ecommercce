package com.techstore.ecommerce.service;

import com.techstore.ecommerce.object.constant.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

  private final OrderService orderService;

  // Khi đơn hàng được bên giao hàng tiếp nhận =>
  // set trạng thái đơn hàng thành đang giao
  public void deliveryPending(long id) {
    orderService.deliveryOrder(id, OrderStatus.PENDING);
  }

  public void deliveryProcessed(long id) {
    orderService.deliveryOrder(id, OrderStatus.PROCESSED);
  }

  public void deliveryDelivering(long id) {
    orderService.deliveryOrder(id, OrderStatus.DELIVERING);
  }

  public void deliveryReceived(long id) {
    orderService.deliveryOrder(id, OrderStatus.RECEIVED);
  }

  public void deliveryDeclined(long id) {
    orderService.deliveryOrder(id, OrderStatus.DECLINED);
  }
}
