package com.example.coffeeshop.services;


import com.example.coffeeshop.model.customer.Customer;
import com.example.coffeeshop.repos.CustomerRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {

    CustomerService customerService =new CustomerServiceImpl(null);

    // Test checkCustomerDetailsCorrect method
    @Test
    void checkCustomerDetailsCorrectIsTrue() {
        Customer testCustomer = new Customer("Mbuso", "12345");
        assertTrue(customerService.checkCustomerDetailsCorrect(testCustomer));
    }

    @Test
    void checkCustomerDetailsCorrectIsFalseNoName() {
        Customer testCustomer = new Customer("", "12345");
        assertFalse(customerService.checkCustomerDetailsCorrect(testCustomer));
    }

    @Test
    void checkCustomerDetailsCorrectIsFalseNoPassword() {
        Customer testCustomer = new Customer("Mbuso", "");
        assertFalse(customerService.checkCustomerDetailsCorrect(testCustomer));
    }

    @Test
    void checkCustomerDetailsCorrectIsFalseEmptyString() {
        Customer testCustomer = new Customer("", "");
        assertFalse(customerService.checkCustomerDetailsCorrect(testCustomer));
    }

    @Test
    void checkCustomerDetailsCorrectIsFalsePasswordIsLessThan4Characters() {
        Customer testCustomer = new Customer("Mbuso", "123");
        assertFalse(customerService.checkCustomerDetailsCorrect(testCustomer));
    }



}