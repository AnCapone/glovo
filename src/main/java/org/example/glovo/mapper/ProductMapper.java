package org.example.glovo.mapper;

import org.example.glovo.dto.ProductDto;
import org.example.glovo.entity.ProductEntity;

public class ProductMapper {
    public static ProductDto toDto(ProductEntity productEntity) {
        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .description(productEntity.getDescription())
                .build();
    }

    public static ProductEntity toEntity(ProductDto productDto) {
        return ProductEntity.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .build();
    }
}
