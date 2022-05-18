package com.example.backenddemo.repos;

import com.example.backenddemo.entities.OrderEntity;
import com.example.backenddemo.model.order.Status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findAllByNameEquals(String name);
    OrderEntity findAllByStatusEquals(Status status);
}
