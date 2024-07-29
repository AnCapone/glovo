package org.example.glovo.controller;

import lombok.AllArgsConstructor;
import org.example.glovo.dto.ProductDto;
import org.example.glovo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping()
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable int id) {
        return productService.getById(id);
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }
}
