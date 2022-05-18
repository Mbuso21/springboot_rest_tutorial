package com.example.coffeeshop.services;

import com.example.coffeeshop.model.order.PlaceOrderResponse;
import com.example.coffeeshop.model.order.Order;

public interface OrderService {
    PlaceOrderResponse placeOrder(Order order);

    public String nowDateInCorrectFormat();

}
