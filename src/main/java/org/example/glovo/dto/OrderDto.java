package org.example.glovo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderDto {
    private int id;
    private int orderNumber;
    private List<Integer> itemsId;

}
