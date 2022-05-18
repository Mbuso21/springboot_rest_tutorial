package com.example.backenddemo.model.customer;

import lombok.Data;
import java.io.Serializable;

@Data
public class RegisterResponse implements Serializable {
    private String message;
    private boolean success;
}
