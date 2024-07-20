package org.example.glovo.repository;

import lombok.AllArgsConstructor;
import org.example.glovo.dto.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@AllArgsConstructor
@Repository
public class OrderRepository {
    private final Map<Integer, Order> orders = new HashMap<>();

    public Order getOrder(int id) {
        return orders.get(id);
    }

    public void deleteOrder(int id) {
        orders.remove(id);
    }

    public Order updateOrder(Order order) {
        List<String> unupdatedProducts = orders.get(order.getId()).getProductsList();
        order.setProductsList(Optional.ofNullable(order.getProductsList()).orElse(unupdatedProducts));
        orders.put(order.getId(), order);
        return order;
    }

    public Order createOrder(Order order) {
        order.setProductsList(new ArrayList<>());
        return orders.put(order.getId(), order);
    }

    public Order addProductToOrder(int orderId, String product) {
        Order order = orders.get(orderId);
        order.getProductsList().add(product);
        return order;
    }

    public Order removeProductFromOrder(int orderId, String product) {
        Order order = orders.get(orderId);
        order.getProductsList().remove(product);
        return order;
    }
}
