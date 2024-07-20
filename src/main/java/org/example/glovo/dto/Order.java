package org.example.glovo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Order {
    private int id;
    private String customerName;
    private List<String> productsList;

}
