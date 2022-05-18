package com.example.coffeeshop.model.customer;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class CustomerLoginRequest implements Serializable {
    @NotEmpty(message = "Customer name is required")
    private String customerName;
    @NotEmpty(message = "Password name is required")
    private String password;
}
