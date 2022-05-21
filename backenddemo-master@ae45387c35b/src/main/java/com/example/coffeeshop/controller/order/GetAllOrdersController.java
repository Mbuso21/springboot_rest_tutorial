package com.example.coffeeshop.controller.order;

import com.example.coffeeshop.model.order.getorders.GetAllOrdersResponse;
import com.example.coffeeshop.services.OrderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/coffeeshop/v1/customer/")
public class GetAllOrdersController {

    private final OrderService orderService;

    public GetAllOrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAllOrdersResponse> getOrdersByName() {

        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
