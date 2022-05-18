package com.example.coffeeshop.services;

import com.example.coffeeshop.model.order.MakeOrderResponse;
import com.example.coffeeshop.model.order.Order;

public interface OrderService {
    MakeOrderResponse placeOrder(Order order);

}
