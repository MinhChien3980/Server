package org.example.server_mobile.service;

import java.util.List;

import org.example.server_mobile.entity.Order;
import org.example.server_mobile.exception.ApiException;
import org.example.server_mobile.repository.OrderRepo;
import org.springframework.stereotype.Service;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    OrderRepo orderRepo;

    public List<Order> getOrders() {
        return orderRepo.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepo.findById(id).orElseThrow(
                () -> new ApiException(404, "Order not found"));
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepo.findByUserId(userId);
    }

    public void changeOrderStatus(Long id, String status) {
        Order order = orderRepo.findById(id).orElseThrow(
                () -> new ApiException(404, "Order not found"));
        order.setStatus(status);
        orderRepo.save(order);
    }

}
