package com.example.coffeeshop.repos;

import com.example.coffeeshop.entities.OrderEntity;
import com.example.coffeeshop.model.order.Status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findAllByNameEquals(String name);
}
