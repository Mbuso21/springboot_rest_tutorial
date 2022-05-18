package com.example.coffeeshop.model.order;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MakeOrderRequest {
    @NotNull(message = "Order details required")
    private Order order;
}
