package com.example.coffeeshop.model.order;

import lombok.Data;
import java.io.Serializable;

@Data
public class MakeOrderResponse implements Serializable {

    private String message;
    private boolean success;
}