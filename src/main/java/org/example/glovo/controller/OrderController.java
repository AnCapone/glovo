package org.example.glovo.controller;

import lombok.AllArgsConstructor;
import org.example.glovo.dto.ItemDto;
import org.example.glovo.dto.OrderDto;
import org.example.glovo.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Service
public class OrderController {

    private OrderService orderService;


    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable int id) {
        return orderService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {

        orderService.delete(id);
    }

    @GetMapping()
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public OrderDto save(@RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @GetMapping("/{id}/items")
    public OrderDto addItem(@RequestBody ItemDto itemDto, @PathVariable int id) {
        return orderService.addItem(itemDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        orderService.delete(id);
    }

}
