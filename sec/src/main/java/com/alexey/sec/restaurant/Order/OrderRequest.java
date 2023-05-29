package com.alexey.sec.restaurant.Order;

import com.alexey.sec.store.restaurant.DishForRequest;
import com.alexey.sec.store.restaurant.statusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<DishForRequest> dishes;
    private statusEnum status;
    private String special_requests;
}
