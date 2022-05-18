package com.example.coffeeshop.entities;

import com.example.coffeeshop.model.order.OrderDescription;
import com.example.coffeeshop.model.order.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;
    @Column()
    private String name;
    private String description;
    private String date;
    private Status status;
}
