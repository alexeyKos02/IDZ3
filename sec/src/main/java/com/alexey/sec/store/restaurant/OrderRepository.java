package com.alexey.sec.store.restaurant;

import com.alexey.sec.store.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findOrderById(Integer id);
    Stream<Order> findOrdersByUser(User user);

    Stream<Order> getOrdersByStatus(statusEnum status);
}
