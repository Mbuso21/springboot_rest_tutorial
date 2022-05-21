package com.example.coffeeshop.controller.order;


import com.example.coffeeshop.model.order.getorders.GetAllOrdersResponse;
import com.example.coffeeshop.model.order.placeorders.PlaceOrderResponse;
import com.example.coffeeshop.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/coffeeshop/v1/customer/")
public class GetAllOrdersByNameController {

    private final OrderService orderService;

    public GetAllOrdersByNameController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/order/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAllOrdersResponse> getOrdersByName(@PathVariable String name) {
        GetAllOrdersResponse response = orderService.getAllOrdersByName(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
