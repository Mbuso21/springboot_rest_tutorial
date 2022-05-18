package com.example.coffeeshop.model.customer;

import lombok.Data;
import java.io.Serializable;

@Data
public class RegisterResponse implements Serializable {
    private String message;
    private boolean success;
}
