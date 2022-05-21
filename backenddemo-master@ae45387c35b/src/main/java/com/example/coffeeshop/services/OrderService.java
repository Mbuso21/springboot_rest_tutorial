package com.example.coffeeshop.services;

import com.example.coffeeshop.model.order.getorders.GetAllOrdersResponse;
import com.example.coffeeshop.model.order.placeorders.PlaceOrderResponse;
import com.example.coffeeshop.model.order.Order;
import com.example.coffeeshop.model.order.Status;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;

public interface OrderService {
    PlaceOrderResponse placeOrder(Order order);
    GetAllOrdersResponse getAllOrders();
    GetAllOrdersResponse getAllOrdersByName(String name);
    GetAllOrdersResponse getAllOrdersByStatus(Status status);
    PlaceOrderResponse responseForIncorrectInputs(Order order, PlaceOrderResponse response);
    String nowDateInCorrectFormat();
    Order newOrder(String requestString) throws JSONException;

    HttpStatus setHttpStatus(PlaceOrderResponse response, String order);
}
