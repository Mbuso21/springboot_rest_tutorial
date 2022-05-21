package com.example.coffeeshop.services;

import com.example.coffeeshop.model.customer.Customer;
import com.example.coffeeshop.model.customer.CustomerRequest;
import com.example.coffeeshop.model.customer.CustomerResponse;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;

public interface CustomerService {
    CustomerResponse registerCustomer(Customer customer);
    CustomerResponse login(CustomerRequest customerRequest);
    boolean checkCustomerDetailsCorrect(Customer customer);
    boolean checkIfCustomerExists(Customer customer);
    HttpStatus setHttpStatus(CustomerResponse response, String endPoint);
    JSONObject convertJsonStringToObject(String jasonString);
    Customer newCustomer(String jsonString) throws JSONException;

}
