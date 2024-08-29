package org.example.glovo.service;

import lombok.AllArgsConstructor;
import org.example.glovo.dto.ProductDto;
import org.example.glovo.entity.ProductEntity;
import org.example.glovo.repository.ProductRepository;
import org.example.glovo.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {
    private ProductRepository productRepository;

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(ProductMapper::toDto).toList();
    }

    public ProductDto save(ProductDto productDto) {
        ProductEntity productEntity = productRepository.save(ProductMapper.toEntity(productDto));
        return ProductMapper.toDto(productEntity);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public ProductDto getById(int id) {
        return ProductMapper.toDto(productRepository.findById(id).orElseThrow());
    }

    public ProductDto update(ProductDto productDto) {
        ProductEntity productEntity = productRepository.save(ProductMapper.toEntity(productDto));
        return ProductMapper.toDto(productEntity);
    }
}
