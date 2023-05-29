package com.alexey.sec.restaurant.orderDish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishRequest {
    private Integer price;
    private Integer quantity;
    private String description;
    private String name;
}
