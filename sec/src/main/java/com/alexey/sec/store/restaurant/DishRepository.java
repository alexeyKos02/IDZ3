package com.alexey.sec.store.restaurant;

import com.alexey.sec.store.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    Optional<Dish> findDishByName(String name);
    Optional<Dish> findDishById(Integer id);

    Stream<Dish> getDishesByQuantityGreaterThan(Integer number);
}
