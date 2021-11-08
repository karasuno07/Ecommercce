package com.techstore.ecommerce.service;

import com.techstore.ecommerce.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.constant.OrderStatus;
import com.techstore.ecommerce.object.dto.filter.OrderFilter;
import com.techstore.ecommerce.object.dto.request.OrderRequest;
import com.techstore.ecommerce.object.entity.jpa.Order;
import com.techstore.ecommerce.object.entity.jpa.OrderDetail;
import com.techstore.ecommerce.object.mapper.OrderDetailMapper;
import com.techstore.ecommerce.object.mapper.OrderMapper;
import com.techstore.ecommerce.repository.jpa.OrderRepository;
import com.techstore.ecommerce.repository.jpa.spec.OrderSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;

    public Page<Order> findAllOrder(OrderFilter filter) {
        Specification<Order> specification = OrderSpec.getSpecification(filter);
        return orderRepo.findAll(specification, filter.getPagination().getPageAndSort());
    }

    public Order findOrderById(long id) {
        return orderRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found any order with id " + id));
    }

    public Order createOrder(OrderRequest request) {
        Order order = orderMapper.createEntityFromRequest(request);

        List<OrderDetail> details = request.getDetails().stream()
                                           .map(orderDetailMapper::createEntityFromRequest)
                                           .collect(Collectors.toList());
        order.setDetails(details);
        order.setStatus(OrderStatus.PENDING.getStatus());

        return orderRepo.save(order);
    }

    public void deleteOrder(long id) {
        Order order = findOrderById(id);
        if (order.getStatus().equals(OrderStatus.PENDING.getStatus())) {
            orderRepo.delete(order);
        } else {
            // TODO: tạo exception class để throw
        }
    }

    // TODO: xác nhận đơn hàng (từ phía nhân viên)
    // TODO:


}
