package com.example.coffeeshop.services;

import com.example.coffeeshop.repos.OrderRepo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    OrderService orderService = new OrderServiceImpl(null);

    @Test
    void nowDateInCorrectFormatTest() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime date = LocalDateTime.now();
        assertEquals(dateTimeFormat.format(date), orderService.nowDateInCorrectFormat());
    }
}