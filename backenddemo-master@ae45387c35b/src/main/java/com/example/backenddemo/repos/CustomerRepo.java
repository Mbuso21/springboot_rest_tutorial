package com.example.backenddemo.repos;

import com.example.backenddemo.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {
    CustomerEntity findByNameEquals(String customer);
}
