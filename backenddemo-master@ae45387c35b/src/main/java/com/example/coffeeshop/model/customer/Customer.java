package com.example.coffeeshop.model.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    @NotEmpty(message = "Customer name is required")
    private String name;
    @NotEmpty(message = "Password name is required")
    private String password;
}
