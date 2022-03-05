package com.techstore.ecommerce.service;

import com.techstore.ecommerce.object.constant.OrderStatus;
import com.techstore.ecommerce.object.dto.filter.OrderFilter;
import com.techstore.ecommerce.object.dto.mapper.OrderDetailMapper;
import com.techstore.ecommerce.object.dto.mapper.OrderMapper;
import com.techstore.ecommerce.object.dto.request.OrderRequest;
import com.techstore.ecommerce.object.entity.jpa.Order;
import com.techstore.ecommerce.object.entity.jpa.OrderDetail;
import com.techstore.ecommerce.object.entity.jpa.User;
import com.techstore.ecommerce.object.exception.ResourceNotFoundException;
import com.techstore.ecommerce.repository.OrderRepository;
import com.techstore.ecommerce.repository.spec.OrderSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;

    private final UserService userService;

    public Page<Order> findAllOrders(OrderFilter filter) {
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
        order.setStatus(OrderStatus.PENDING);

        if (!ObjectUtils.isEmpty(request.getUserId())) {
            User user = userService.findUserById(request.getUserId());
            order.setUser(user);
        }

        return orderRepo.save(order);
    }

    public void deleteOrder(long id) {
        Order order = findOrderById(id);
        if (order.getStatus().equals(OrderStatus.PENDING)) {
            orderRepo.delete(order);
        } else {
            // TODO: tạo exception class để throw
        }
    }

    // xác nhận đơn hàng (từ phía nhân viên)
    public void deliveryOrder(long id, OrderStatus status) {
        Order order = findOrderById(id);
        order.setStatus(status);
        orderRepo.save(order);
    }

}
