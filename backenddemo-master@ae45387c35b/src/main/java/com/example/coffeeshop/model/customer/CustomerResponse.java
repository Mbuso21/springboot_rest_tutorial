package com.example.coffeeshop.model.customer;

import lombok.Data;
import java.io.Serializable;
@Data
public class CustomerResponse implements Serializable {
    private String message;
    private boolean success;
}
