package org.example.glovo.repository;

import lombok.AllArgsConstructor;
import org.example.glovo.dto.OrderDto;
import org.example.glovo.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

}
