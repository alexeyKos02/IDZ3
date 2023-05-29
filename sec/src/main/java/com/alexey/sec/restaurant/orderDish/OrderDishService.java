package com.alexey.sec.restaurant.orderDish;


import com.alexey.sec.exception.BadRequestException;
import com.alexey.sec.exception.NotFoundException;
import com.alexey.sec.store.restaurant.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDishService {
    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;
    private final OrderDishRepository orderDishRepository;

    public Double createOrderDish(Integer id, Integer count, Order order) {
        var dish = dishRepository.findDishById(id);
        if (dish.isEmpty()) {
            throw new NotFoundException("Блюда нет в меню");
        }
        if (count < 0 || count > dish.get().getQuantity()) {
            throw new BadRequestException("некорректное число блюд");
        }
        var afterCount = dish.get().getQuantity() - count;
        dish.get().setQuantity(afterCount);
        var dishOrder = OrderDish.builder()
                .order(order)
                .dish(dish.get())
                .price(dish.get().getPrice() * count)
                .quantity(count).build();
        orderDishRepository.saveAndFlush(dishOrder);
        return dish.get().getPrice() * count;
    }
}
