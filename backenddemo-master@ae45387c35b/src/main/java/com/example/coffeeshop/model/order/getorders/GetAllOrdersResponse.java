package com.example.coffeeshop.model.order.getorders;

import com.example.coffeeshop.entities.OrderEntity;
import lombok.Data;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.Serializable;
import java.util.List;

@Data
public class GetAllOrdersResponse implements Serializable {

    private List<OrderEntity> Orders;
}
