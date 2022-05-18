package com.example.backenddemo.entities;

import com.example.backenddemo.model.order.OrderDescription;
import com.example.backenddemo.model.order.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;
    @Column()
    private String name;
    private OrderDescription description;
    private Date date;
    private Status status;
}
