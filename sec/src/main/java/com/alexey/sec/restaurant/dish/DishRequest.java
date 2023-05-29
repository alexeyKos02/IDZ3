package com.alexey.sec.restaurant.dish;

import com.alexey.sec.store.restaurant.DishForRequest;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishRequest {
    @Nullable
    private Integer id;
    @Nullable
    private String description;
    @Nullable
    private String name;
    @Nullable
    private Double price;
    @Nullable
    private Integer quantity;
}
