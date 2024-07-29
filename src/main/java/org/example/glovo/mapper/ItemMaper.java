package org.example.glovo.mapper;

import org.example.glovo.dto.ItemDto;
import org.example.glovo.entity.ItemEntity;

public class ItemMaper {
    public static ItemDto toDto(ItemEntity itemEntity) {
        return ItemDto.builder()
                .id(itemEntity.getId())
                .quantity(itemEntity.getQuantity())
                .build();
    }

    public static ItemEntity toEntity(ItemDto itemDto) {
        return ItemEntity.builder()
                .id(itemDto.getId())
                .quantity(itemDto.getQuantity())
                .build();
    }
}
