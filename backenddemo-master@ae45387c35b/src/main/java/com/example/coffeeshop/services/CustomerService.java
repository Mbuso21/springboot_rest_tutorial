package com.example.coffeeshop.services;

import com.example.coffeeshop.model.customer.Customer;
import com.example.coffeeshop.model.customer.CustomerRequest;
import com.example.coffeeshop.model.customer.CustomerResponse;

public interface CustomerService {
    CustomerResponse registerCustomer(Customer customer);
    CustomerResponse login(CustomerRequest customerRequest);
    public boolean checkCustomerDetailsCorrect(Customer customer);
    public boolean checkIfCustomerExists(Customer customer);

}
