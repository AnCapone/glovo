package org.example.glovo.controller;

import org.example.glovo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void getOrdersTest() throws Exception {
        OrderDTO order = OrderDTO.builder().id(1).build();
        List<OrderDTO> orders = List.of(order);

        when(orderService.findAll()).thenReturn(orders);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(order.getId()));
    }

    @Test
    void getOrderTest() throws Exception {
        OrderDTO order = OrderDTO.builder().id(1).build();

        when(orderService.findById(order.getId())).thenReturn(order);

        mockMvc.perform(get("/orders/{id}", order.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(order.getId()));
    }

    @Test
    void deleteOrderTest() throws Exception {
        OrderDTO order = OrderDTO.builder().id(1).build();

        doNothing().when(orderService).delete(order.getId());

        mockMvc.perform(delete("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}"))
                .andExpect(status().isOk());

        verify(orderService, times(1)).delete(order.getId());
    }

    @Test
    void saveOrderTest() throws Exception {
        OrderDTO order = OrderDTO.builder().id(1).build();

        when(orderService.save(any(OrderDTO.class))).thenReturn(order);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(order.getId()));

}
