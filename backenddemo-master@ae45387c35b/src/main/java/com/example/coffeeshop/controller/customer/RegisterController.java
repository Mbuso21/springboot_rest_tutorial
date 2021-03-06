package com.example.coffeeshop.controller.customer;

import com.example.coffeeshop.model.customer.Customer;
import com.example.coffeeshop.model.customer.CustomerResponse;
import com.example.coffeeshop.services.CustomerService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/coffeeshop/v1/customer")
public class RegisterController {
    private final CustomerService customerService;

    public RegisterController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody String customerRequestString) throws JSONException {

        Customer newCustomer = customerService.newCustomer(customerRequestString);
        String endPoint = "register";
        CustomerResponse response = customerService.registerCustomer(newCustomer);
        HttpStatus httpStatus = customerService.setHttpStatus(response, endPoint);
        return new ResponseEntity<>(response, httpStatus);

    }



}
