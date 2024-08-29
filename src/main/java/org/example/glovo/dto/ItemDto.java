package org.example.glovo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {
    private int id;
    private int productId;
    private int quantity;
}
