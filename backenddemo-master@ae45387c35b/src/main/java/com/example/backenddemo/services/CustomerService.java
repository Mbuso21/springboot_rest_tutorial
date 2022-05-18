package com.example.backenddemo.services;

import com.example.backenddemo.model.customer.Customer;
import com.example.backenddemo.model.customer.CustomerLoginRequest;
import com.example.backenddemo.model.customer.CustomerLoginResponse;
import com.example.backenddemo.model.customer.RegisterResponse;

public interface CustomerService {
    RegisterResponse registerCustomer(Customer customer);
    CustomerLoginResponse login(CustomerLoginRequest customerLoginRequest);
    public boolean checkCustomerDetailsCorrect(Customer customer);
    public boolean checkIfCustomerExists(Customer customer);

}
