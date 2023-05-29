package com.alexey.sec.restaurant.dish;

import com.alexey.sec.config.JwtService;
import com.alexey.sec.exception.BadRequestException;
import com.alexey.sec.exception.ForbiddenRequestException;
import com.alexey.sec.exception.NotFoundException;
import com.alexey.sec.store.restaurant.*;
import com.alexey.sec.store.user.Role;
import com.alexey.sec.store.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class DishService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final OrderDishRepository orderDishRepository;
    private final OrderRepository orderRepository;

    private void checkUserRole(HttpServletRequest request2) {
        final String authHeader = request2.getHeader("Authorization");
        var jwt = authHeader.substring(7);
        var user = userRepository.findByEmail(jwtService.extractUsername(jwt));
        if (user.isEmpty()) {
            throw new BadRequestException("Токен");
        }
        if (user.get().getRole() == Role.USER) {
            throw new ForbiddenRequestException("Нет прав");
        }
    }

    public String create(DishRequest request, HttpServletRequest request2) {
        checkUserRole(request2);
        if (request.getPrice() < 0 || request.getQuantity() < 0) {
            throw new BadRequestException("Некорректные данные");
        }
        var dish = Dish.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity()).build();
        dishRepository.save(dish);
        return "Блюдо добавлено";
    }

    @Transactional
    public String delete(Integer id, HttpServletRequest request2) {
        checkUserRole(request2);
        if (dishRepository.findDishById(id).isEmpty()) {
            throw new NotFoundException("Такого блюда нет");
        }
        var list = orderDishRepository.findAllByDishId(id).toList();
        Set<Integer> all = new HashSet<>();
        for (var orderDish : list) {
            all.add(orderDish.getOrder().getId());
        }
        boolean check = true;
        for (var orderId : all) {
            if (orderRepository.findOrderById(orderId).get().getStatus() != statusEnum.FINISHED) {
                check = false;
            }
        }
        if (check) {
            for (var orderId : all) {
                orderDishRepository.deleteById(orderId);
            }
            dishRepository.deleteById(id);
            return "блюдо удалено";
        }
        throw new BadRequestException("Невозможно удалить блюдо");
    }

    public Dish get(Integer id, HttpServletRequest request2) {
        checkUserRole(request2);
        if (dishRepository.findDishById(id).isEmpty()) {
            throw new NotFoundException("Такого блюда нет");
        }
        return dishRepository.findDishById(id).get();
    }

    public String put(DishRequest request, HttpServletRequest request2) {
        checkUserRole(request2);
        var dish = dishRepository.findDishById(request.getId()).orElseThrow(() -> new NotFoundException("Такого блюда нет"));
        if (request.getName() != null) {
            dish.setName(request.getName());
        }
        if (request.getDescription() != null) {
            dish.setDescription(request.getDescription());
        }
        if (request.getPrice() != null && request.getPrice() >= 0) {
            dish.setPrice(request.getPrice());
        }
        if (request.getQuantity() != null && request.getQuantity() >= 0) {
            dish.setQuantity(request.getQuantity());
        }
        dishRepository.save(dish);
        return "Блюдо измененно";
    }

    @Transactional
    public List<Dish> getAll() {
        return dishRepository.getDishesByQuantityGreaterThan(0).toList();
    }
}
