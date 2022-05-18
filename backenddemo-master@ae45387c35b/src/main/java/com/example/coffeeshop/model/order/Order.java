package com.example.coffeeshop.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @NotEmpty(message = "Customer name is required")
    private String name;
    @NotEmpty(message = "Description is required")
    private OrderDescription description;
    private Date date;
    private Status status;


}
