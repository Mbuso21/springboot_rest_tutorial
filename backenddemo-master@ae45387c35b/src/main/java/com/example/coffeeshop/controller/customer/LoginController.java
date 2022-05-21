package com.example.coffeeshop.controller.customer;


import com.example.coffeeshop.model.customer.CustomerRequest;
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
public class LoginController {
    private final CustomerService customerService;

    public LoginController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> login(@RequestBody CustomerRequest customerRequest) throws JSONException {
        
        CustomerResponse response = customerService.login(customerRequest);
        HttpStatus httpStatus = customerService.setHttpStatus(response, "login");
        return new ResponseEntity<>(response, httpStatus);
    }
}
