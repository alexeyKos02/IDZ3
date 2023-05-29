package com.alexey.sec.restaurant.Order;

import com.alexey.sec.config.JwtService;
import com.alexey.sec.exception.BadRequestException;
import com.alexey.sec.restaurant.orderDish.OrderDishService;
import com.alexey.sec.store.restaurant.Order;
import com.alexey.sec.store.restaurant.OrderDto;
import com.alexey.sec.store.restaurant.OrderRepository;
import com.alexey.sec.store.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final JwtService jwtService;
    private final OrderRepository repository;
    private final UserRepository userRepository;
    private final OrderDishService orderDishService;
    private final OrderRepository orderRepository;

    public OrderResponse create(OrderRequest request, @NonNull HttpServletRequest requestJwt) {
        Date date = new Date();
        var jwt = requestJwt.getHeader("Authorization").substring(7);
        var user = userRepository.findByEmail(jwtService.extractUsername(jwt));
        var dishes = request.getDishes();
        var order = Order.builder()
                .user(user.get())
                .status(request.getStatus())
                .specialRequests(request.getSpecial_requests())
                .createdAt(new Timestamp(date.getTime()))
                .updatedAt(new Timestamp(date.getTime())).build();
        order = repository.saveAndFlush(order);
        for (var dish : dishes) {
            orderDishService.createOrderDish(dish.getId(), dish.getCount(), order);
        }
        repository.save(order);
        return OrderResponse.builder().message("Заказ принят.").build();
    }
    @Transactional
    public List<OrderDto> get(@NonNull HttpServletRequest requestJwt) {
        var jwt = requestJwt.getHeader("Authorization").substring(7);
        var user = userRepository.findByEmail(jwtService.extractUsername(jwt));
        if(user.isEmpty()){
            throw new BadRequestException("не удалось распознать пользователя");
        }
        var ordersDto = orderRepository.findOrdersByUser(user.get()).map(order -> {
            return new OrderDto(order.getStatus(),order.getSpecialRequests());
        });
        return ordersDto.toList();
    }
}
