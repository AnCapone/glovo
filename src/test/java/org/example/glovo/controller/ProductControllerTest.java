package org.example.glovo.controller;

import org.junit.jupiter.api.BeforeEach;
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
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        ProductDto product = ProductDto.builder().id(1).build();
    }

    private ProductDto productDto;

    @Test
    void getAllTest() throws Exception {
        List<ProductDto> products = List.of(productDto);

        when(productService.getAll()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(productDto.getId()));
    }

    @Test
    void getProductTest() throws Exception {
        when(productService.getById(productDto.getId())).thenReturn(productDto);

        mockMvc.perform(get("/products/{id}", productDto.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productDto.getId()));
    }
}
