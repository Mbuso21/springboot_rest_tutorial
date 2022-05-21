package com.example.coffeeshop.controller.order;

import com.example.coffeeshop.model.customer.CustomerResponse;
import com.example.coffeeshop.model.order.Order;
import com.example.coffeeshop.model.order.placeorders.PlaceOrderResponse;
import com.example.coffeeshop.model.order.Status;
import com.example.coffeeshop.services.CustomerService;
import com.example.coffeeshop.services.OrderService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/coffeeshop/v1/customer/")
public class PlaceOrderController {

    private final OrderService orderService;
    private final CustomerService customerService;


    public PlaceOrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @PostMapping(value = "/order", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody String requestString) throws JSONException {

        Order newOrder = orderService.newOrder(requestString);
        PlaceOrderResponse response = orderService.placeOrder(newOrder);
        HttpStatus httpStatus = orderService.setHttpStatus(response, "order");
        return new ResponseEntity<>(response, httpStatus);
    }



}
