package com.example.coffeeshop.model.order.getorders;

import lombok.Data;
import java.io.Serializable;

@Data
public class GetAllOrdersResponse implements Serializable {

    private String jsonStringOfOrders;
}
