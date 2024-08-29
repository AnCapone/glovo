package org.example.glovo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.glovo.dto.ProductDto;
import org.example.glovo.repository.ProductRepository;
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
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    private final ObjectMapper mapper = new ObjectMapper();

    private ProductDto productDto1 = ProductDto.builder()
            .id(1)
            .name("Pizza")
            .price(10.0)
            .description("Tasty")
            .build();
    private ProductDto productDto2 = ProductDto.builder()
            .id(2)
            .name("Burger")
            .price(5.0)
            .description("Delicious")
            .build();

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void getAllTest() throws Exception {
        productDto1 = productService.save(productDto1);
        productDto2 = productService.save(productDto2);

        mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(productDto1, productDto2)), true));
    }

    @Test
    public void getByIdTest() throws Exception {
        productDto1 = productService.save(productDto1);

        mockMvc.perform(get("/products/" + productDto1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(productDto1), true));
    }

    @Test
    public void createTest() throws Exception {
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(productDto1)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(productDto1), true));
    }

    @Test
    public void deleteTest() throws Exception {
        productDto1 = productService.save(productDto1);

        mockMvc.perform(delete("/products/" + productDto1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
