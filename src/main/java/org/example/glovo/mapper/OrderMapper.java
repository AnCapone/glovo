package org.example.glovo.mapper;

import org.example.glovo.dto.OrderDto;
import org.example.glovo.entity.ItemEntity;
import org.example.glovo.entity.OrderEntity;

public class OrderMapper {
    public static OrderDto toDto(OrderEntity orderEntity) {
        return OrderDto.builder()
                .id(orderEntity.getId())
                .orderNumber(orderEntity.getOrderNumber())
                .itemsId(orderEntity.getItems().stream().map(ItemEntity::getId).toList())
                .build();
    }

    public static OrderEntity toEntity(OrderDto orderDto) {
        return OrderEntity.builder()
                .id(orderDto.getId())
                .orderNumber(orderDto.getOrderNumber())
                .build();
    }
}
