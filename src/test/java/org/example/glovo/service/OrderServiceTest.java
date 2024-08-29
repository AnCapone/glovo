package org.example.glovo.service;

import org.example.glovo.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private final OrderRepository orderRepository = mock(OrderRepository.class);

    @Test
    void getOrderTest() {
        OrderEntity order = OrderEntity.builder().id(1).build();
        when(orderRepository.findById(any())).thenReturn(Optional.ofNullable(order));

        Assertions.assertEquals(order.getId(), orderRepository.findById(1).get().getId());
    }

    @Test
    public void deleteTest() {

        OrderService orderService = new OrderService(orderRepository);

        OrderEntity orderEntity = OrderEntity.builder().id(1).items(List.of(ItemEntity.builder().id(1).build())).build();

        Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.ofNullable(orderEntity));

        orderService.delete(1);

        Mockito.verify(orderRepository, Mockito.times(1)).findById(anyInt());
        Mockito.verify(orderRepository, Mockito.times(1)).deleteById(anyInt());

    }

    @Test
    public void findAllTest() {
        OrderEntity order1 = new OrderEntity();
        OrderEntity order2 = new OrderEntity();
        List<OrderEntity> ordersEntity = Arrays.asList(order1, order2);
        Mockito.when(orderRepository.findAll()).thenReturn(ordersEntity);

        OrderDto dto1 = new OrderDTO();
        OrderDto dto2 = new OrderDTO();
        List<OrderDto> DTOs = Arrays.asList(dto1, dto2);

        OrderService orderService = new OrderService(orderRepository, itemRepository);
        Assertions.assertEquals(DTOs, orderService.findAll());

        Mockito.verify(orderRepository, Mockito.times(1)).findAll();
    }
}
