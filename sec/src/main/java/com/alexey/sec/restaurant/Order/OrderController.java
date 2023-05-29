package com.alexey.sec.restaurant.Order;
import com.alexey.sec.store.restaurant.Order;
import com.alexey.sec.store.restaurant.OrderDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    @PostMapping("/create")
    public ResponseEntity<String> createOrder(
            @NonNull HttpServletRequest request2,
            @RequestBody OrderRequest request
    ){
        return ResponseEntity.ok(String.valueOf(service.create(request,request2)));
    }
    @GetMapping("/get")
    public ResponseEntity<List<OrderDto>> getOrder(
            @NonNull HttpServletRequest request2
    ){
        return ResponseEntity.ok(service.get(request2));
    }
}