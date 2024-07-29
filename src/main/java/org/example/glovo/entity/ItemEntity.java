package org.example.glovo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    private int quantity;
}
