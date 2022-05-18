package com.example.backenddemo.model.customer;

import java.io.Serializable;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterRequest implements Serializable {
    @NotNull(message = "Customer details required")
    private Customer customer;

}
