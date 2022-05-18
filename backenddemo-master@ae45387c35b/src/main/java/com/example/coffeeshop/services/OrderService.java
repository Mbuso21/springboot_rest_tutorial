package com.example.coffeeshop.services;

import com.example.coffeeshop.model.order.getorders.GetAllOrdersResponse;
import com.example.coffeeshop.model.order.placeorders.PlaceOrderResponse;
import com.example.coffeeshop.model.order.Order;
import com.example.coffeeshop.model.order.Status;

public interface OrderService {
    PlaceOrderResponse placeOrder(Order order);

    public String nowDateInCorrectFormat();

    public PlaceOrderResponse responseForIncorrectInputs(Order order, PlaceOrderResponse response);

    public GetAllOrdersResponse getAllOrdersByName(String name);

    public GetAllOrdersResponse getAllOrdersByStatus(Status status);

}
