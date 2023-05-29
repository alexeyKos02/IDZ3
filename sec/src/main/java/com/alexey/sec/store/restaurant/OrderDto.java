package com.alexey.sec.store.restaurant;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
    @Enumerated(EnumType.STRING)
    private statusEnum status;
    private String specialRequests;
}
