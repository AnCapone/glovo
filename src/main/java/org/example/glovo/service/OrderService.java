package org.example.glovo.service;

import org.example.glovo.dto.Order;
import org.example.glovo.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class OrderService {

    private OrderRepository orderRepository;

    public Order getOrder(int id) {
        return orderRepository.getOrder(id);
    }

    public void deleteOrder(int id) {
        orderRepository.deleteOrder(id);
    }

    public Order updateOrder(Order order) {
        return orderRepository.updateOrder(order);
    }

    public Order createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    public Order addProductToOrder(int orderId, String product) {
        return orderRepository.addProductToOrder(orderId, product);
    }

    public Order removeProductFromOrder(int orderId, String product) {
        return orderRepository.removeProductFromOrder(orderId, product);
    }

}
