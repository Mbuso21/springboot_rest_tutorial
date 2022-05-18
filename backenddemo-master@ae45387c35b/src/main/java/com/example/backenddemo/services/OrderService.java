package com.example.backenddemo.services;

import com.example.backenddemo.model.order.MakeOrderResponse;
import com.example.backenddemo.model.order.Order;

public interface OrderService {
    MakeOrderResponse placeOrder(Order order);

}
