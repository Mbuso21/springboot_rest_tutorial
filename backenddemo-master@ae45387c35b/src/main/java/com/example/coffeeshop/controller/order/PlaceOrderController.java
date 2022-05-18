package com.example.coffeeshop.controller.order;

import com.example.coffeeshop.model.order.Order;
import com.example.coffeeshop.model.order.PlaceOrderRequest;
import com.example.coffeeshop.model.order.PlaceOrderResponse;
import com.example.coffeeshop.model.order.Status;
import com.example.coffeeshop.services.OrderService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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


    public PlaceOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/order", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody String requestString) throws JSONException {

        System.out.println(requestString);
        PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest();
        Order newOrder = newOrder(requestString);
        placeOrderRequest.setOrder(newOrder);

        return ResponseEntity.ok(orderService.placeOrder(placeOrderRequest.getOrder()));
    }

    public static JSONObject convertJsonStringToObject(String jasonString) {
        try {
            return new JSONObject(jasonString);
        } catch (Exception e) {return null;}
    }
    private Order newOrder(String requestString) throws JSONException {
        JSONObject jsonObject = convertJsonStringToObject(requestString);
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");

        return new Order(name,description,"null", Status.PENDING);
    }
}
