package com.alexey.sec.restaurant.dish;

import com.alexey.sec.store.restaurant.Dish;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant/dish")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @PostMapping("/create")
    public ResponseEntity<String> createDish(
            @NonNull HttpServletRequest request2,
            @RequestBody DishRequest request
    ) {
        return ResponseEntity.ok(String.valueOf(dishService.create(request, request2)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDish(
            @NonNull HttpServletRequest request2,
            @PathVariable("id") Integer dishId
    ) {
        return ResponseEntity.ok(String.valueOf(dishService.delete(dishId, request2)));
    }

    @GetMapping("/get_dish/{id}")
    public ResponseEntity<Dish> getDish(
            @PathVariable("id") Integer dishId,
            @NonNull HttpServletRequest request2) {
        return ResponseEntity.ok(dishService.get(dishId, request2));
    }

    @PutMapping("/put")
    public ResponseEntity<String> putDish(
            @NonNull HttpServletRequest request2,
            @RequestBody DishRequest request) {
        return ResponseEntity.ok(dishService.put(request, request2));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Dish>> putDish() {
        return ResponseEntity.ok(dishService.getAll());
    }
}
