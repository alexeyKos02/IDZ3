package com.alexey.sec.store.restaurant;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishForRequest {
    private Integer id;
    private Integer count;
}
