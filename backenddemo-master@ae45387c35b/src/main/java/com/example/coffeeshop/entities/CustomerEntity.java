package com.example.coffeeshop.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity { //customer entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(length = 30, unique = true )
    private String name;
    private String password;

}
