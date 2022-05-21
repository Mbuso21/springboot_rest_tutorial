package com.example.coffeeshop.services;

import com.example.coffeeshop.entities.OrderEntity;
import com.example.coffeeshop.model.order.Status;
import com.example.coffeeshop.model.order.getorders.GetAllOrdersResponse;
import com.example.coffeeshop.model.order.placeorders.PlaceOrderResponse;
import com.example.coffeeshop.model.order.Order;
import com.example.coffeeshop.repos.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepo orderRepo;
    private final CustomerService customerService;

    public OrderServiceImpl(OrderRepo orderRepo, CustomerService customerService) {
        this.orderRepo = orderRepo;
        this.customerService = customerService;
    }
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Override
    public PlaceOrderResponse placeOrder(Order order) {

        order.setDate(nowDateInCorrectFormat());
        PlaceOrderResponse response = new PlaceOrderResponse();
        if(order.getName().length() == 0 || order.getDescription().length() == 0) {
            return responseForIncorrectInputs(order, response);
        }

        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(order, orderEntity);
        OrderEntity dbResponse = orderRepo.save(orderEntity);

        log.info("Db reponse -> {}", dbResponse);

        response.setMessage("Success.");
        response.setSuccess(true);

        return response;
    }

    @Override
    public GetAllOrdersResponse getAllOrders() {

        GetAllOrdersResponse response = new GetAllOrdersResponse();
        Order order = new Order();
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(order, orderEntity);
        List<OrderEntity> dbResponse = orderRepo.findAll();

        log.info("Db reponse -> {}", dbResponse);

        response.setOrders(dbResponse);

        return response;
    }

    @Override
    public GetAllOrdersResponse getAllOrdersByName(String name) {
        GetAllOrdersResponse response = new GetAllOrdersResponse();
        Order order = new Order();
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(order, orderEntity);
        List<OrderEntity> dbResponse = orderRepo.findAll();
        List<OrderEntity> jsonList = new ArrayList<>();
        for(OrderEntity dbItem: dbResponse) {
            if(dbItem.getName().equals(name)) {
                jsonList.add(dbItem);
            }
        }

        log.info("Db reponse -> {}", dbResponse);

        response.setOrders(jsonList);

        return response;
    }

    @Override
    public GetAllOrdersResponse getAllOrdersByStatus(Status status) {
        return null;
    }

    @Override
    public String nowDateInCorrectFormat() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime date = LocalDateTime.now();
        return dateTimeFormat.format(date);
    }

    @Override
    public Order newOrder(String requestString) throws JSONException {
        JSONObject jsonObject = customerService.convertJsonStringToObject(requestString);
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");

        return new Order(name,description,"null", Status.PENDING);
    }

    @Override
    public HttpStatus setHttpStatus(PlaceOrderResponse response, String endPoint) {
        if(endPoint.equals("order") && response.isSuccess()) {
            return HttpStatus.CREATED;
        }
        if(response.isSuccess()) return HttpStatus.OK;
        return HttpStatus.BAD_REQUEST;
    }


    @Override
    public PlaceOrderResponse responseForIncorrectInputs(Order order, PlaceOrderResponse response) {
        if(order.getName().length() == 0 && order.getDescription().length() == 0) {
            response.setMessage("Failed. No details.");
            response.setSuccess(false);

            return response;
        }
        // Checks if the there is no name
        if(order.getName().length() == 0) {
            response.setMessage("Failed. No customer name.");
            response.setSuccess(false);

            return response;
        }
        // Checks if there is no description
        if(order.getDescription().length() == 0) {
            response.setMessage("Failed. No description.");
            response.setSuccess(false);

            return response;
        }
        return response;
    }




}
