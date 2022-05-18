package com.example.coffeeshop.model.order.placeorders;

import lombok.Data;
import java.io.Serializable;

@Data
public class PlaceOrderResponse implements Serializable {

    private String message;
    private boolean success;
}
