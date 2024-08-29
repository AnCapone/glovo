package org.example.glovo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.glovo.dto.OrderDto;
import org.example.glovo.repository.OrderRepository;
import org.example.glovo.service.OrderService;
import org.example.glovo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private OrderDto orderDto1;
    private OrderDto orderDto2;

    @BeforeEach
    public void setUp() {
        orderRepository.deleteAll();
        orderDto1 = OrderDto.builder()
                .id(1)
                .orderNumber(10001)
                .build();
        orderDto2 = OrderDto.builder()
                .id(2)
                .build();
    }

    @Test
    public void getAllTest() throws Exception {
        orderDto1 = orderService.save(orderDto1);
        orderDto2 = orderService.save(orderDto2);

        mockMvc.perform(get("/orders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(orderDto1, orderDto2)), true));

    }

    @Test
    public void getByIdTest() throws Exception {
        orderDto1 = orderService.save(orderDto1);
        orderDto2 = orderService.save(orderDto2);

        mockMvc.perform(get("/orders/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(orderDto1), true));

    }

    @Test
    public void saveTest() throws Exception {
        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(orderDto1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.status").value("IN_PROCESSING"))
                .andExpect(jsonPath("$.checkoutDate").exists());

    }
}
