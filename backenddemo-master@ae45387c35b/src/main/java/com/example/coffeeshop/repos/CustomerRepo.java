package com.example.coffeeshop.repos;

import com.example.coffeeshop.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {
    CustomerEntity findByNameEquals(String customer);
}
