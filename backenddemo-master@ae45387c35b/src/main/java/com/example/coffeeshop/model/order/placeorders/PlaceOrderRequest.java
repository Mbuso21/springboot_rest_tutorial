package com.example.coffeeshop.model.order.placeorders;

import com.example.coffeeshop.model.order.Order;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlaceOrderRequest {
    @NotNull(message = "Order details required")
    private Order order;
}
