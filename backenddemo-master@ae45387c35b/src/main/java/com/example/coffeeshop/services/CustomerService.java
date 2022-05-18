package com.example.coffeeshop.services;

import com.example.coffeeshop.model.customer.Customer;
import com.example.coffeeshop.model.customer.CustomerLoginRequest;
import com.example.coffeeshop.model.customer.CustomerLoginResponse;
import com.example.coffeeshop.model.customer.RegisterResponse;

public interface CustomerService {
    RegisterResponse registerCustomer(Customer customer);
    CustomerLoginResponse login(CustomerLoginRequest customerLoginRequest);
    public boolean checkCustomerDetailsCorrect(Customer customer);
    public boolean checkIfCustomerExists(Customer customer);

}
