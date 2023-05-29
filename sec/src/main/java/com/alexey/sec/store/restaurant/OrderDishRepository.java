package com.alexey.sec.store.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface OrderDishRepository extends JpaRepository<OrderDish, Integer> {
    Optional<OrderDish> findOrderDishByOrderAndDish(Order order, Dish dish);
    Stream<OrderDish> findAllByDishId(Integer id);
}
