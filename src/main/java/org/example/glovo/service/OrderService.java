package org.example.glovo.service;

import lombok.AllArgsConstructor;
import org.example.glovo.dto.ItemDto;
import org.example.glovo.dto.OrderDto;
import org.example.glovo.entity.ItemEntity;
import org.example.glovo.entity.OrderEntity;
import org.example.glovo.entity.ProductEntity;
import org.example.glovo.mapper.ItemMaper;
import org.example.glovo.mapper.OrderMapper;
import org.example.glovo.repository.OrderRepository;
import org.example.glovo.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private OrderRepository orderRepository;


    public List<OrderDto> getAll(){
        return orderRepository.findAll().stream().map(OrderMapper::toDto).toList();
    }

    public OrderDto save(OrderDto orderDto){
        return OrderMapper.toDto(orderRepository.save(OrderMapper.toEntity(orderDto)));
    }

    public OrderDto addItem(ItemDto itemDto, int orderId){
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow();
        ItemEntity itemEntity = ItemMaper.toEntity(itemDto);
        ProductEntity productEntity = productRepository.findById(itemDto.getProductId()).orElseThrow();
        itemEntity.setOrder(orderEntity);
        ProductEntity build = ProductEntity.builder().id(itemEntity.getProduct().getId()).build();
        itemEntity.setProduct(build);
        return OrderMapper.toDto(orderRepository.save(orderEntity));
    }

    public OrderDto getById(int id){
        return OrderMapper.toDto(orderRepository.findById(id).orElseThrow());
    }

    public void delete(int id){
        orderRepository.deleteById(id);
    }
}
