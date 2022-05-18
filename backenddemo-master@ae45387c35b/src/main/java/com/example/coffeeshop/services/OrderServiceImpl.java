package com.example.coffeeshop.services;

import com.example.coffeeshop.entities.OrderEntity;
import com.example.coffeeshop.model.order.Status;
import com.example.coffeeshop.model.order.getorders.GetAllOrdersResponse;
import com.example.coffeeshop.model.order.placeorders.PlaceOrderResponse;
import com.example.coffeeshop.model.order.Order;
import com.example.coffeeshop.repos.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepo orderRepo;

    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
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
    public String nowDateInCorrectFormat() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime date = LocalDateTime.now();
        return dateTimeFormat.format(date);
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

    @Override
    public GetAllOrdersResponse getAllOrdersByName(String name) {
        return null;
    }

    @Override
    public GetAllOrdersResponse getAllOrdersByStatus(Status status) {
        return null;
    }


}
