package com.example.coffeeshop.services;

import com.example.coffeeshop.entities.OrderEntity;
import com.example.coffeeshop.model.order.PlaceOrderResponse;
import com.example.coffeeshop.model.order.Order;
import com.example.coffeeshop.model.order.Status;
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
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(order, orderEntity);
        OrderEntity dbResponse = orderRepo.save(orderEntity);

        log.info("Db reponse -> {}", dbResponse);

        response.setMessage("Success.");
        response.setSuccess(true);

        return response;
    }

    public String nowDateInCorrectFormat() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime date = LocalDateTime.now();
        return dateTimeFormat.format(date);
    }
}
