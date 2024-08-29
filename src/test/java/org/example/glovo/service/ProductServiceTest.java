package org.example.glovo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);

    @Test
    public void getAllTest() {

        ProductService productService = new ProductService(productRepository);

        List<ProductEntity> productEntities = List.of(ProductEntity.builder().id(1).build(), ProductEntity.builder().id(2).build());
        List<ProductDto> productDTOs = List.of(ProductDto.builder().id(1).build(), ProductDto.builder().id(2).build());

        when(productRepository.getAll()).thenReturn(productEntities);

        Assertions.assertEquals(productDTOs, productService.getAll());

        verify(productRepository, times(1)).getAll();
    }

    @Test
    public void getByIdTest() {
        ProductService productService = new ProductService(productRepository);
        int id = 1;

        ProductDto dto = ProductDto.builder().id(id).build();
        ProductEntity entity = ProductEntity.builder().id(id).build();

        when(productRepository.getById(anyInt())).thenReturn(Optional.ofNullable(entity));

        Assertions.assertEquals(dto, productService.getById(id));
    }

}
