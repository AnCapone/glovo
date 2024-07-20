package org.example.glovo.controller;

import lombok.AllArgsConstructor;
import org.example.glovo.dto.Order;
import org.example.glovo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Service
public class OrderController {

    private OrderService orderService;


    @GetMapping("/{id}")
    public Order getOrder(@PathVariable int id) {
        return orderService.getOrder(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable int id, @RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PostMapping("/{orderId}/products")
    public Order addProductToOrder(@PathVariable int orderId, @RequestBody String product) {
        return orderService.addProductToOrder(orderId, product);
    }

    @PutMapping("/{orderId}/products")
    public Order removeProductFromOrder(@PathVariable int orderId, @RequestBody String product) {
        return orderService.removeProductFromOrder(orderId, product);
    }

}
