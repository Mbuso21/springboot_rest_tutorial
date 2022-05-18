package com.example.backenddemo.controller;


import com.example.backenddemo.model.customer.*;
import com.example.backenddemo.services.CustomerService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/coffeeshop/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> registerCustomer(@RequestBody String customerRequestString) throws JSONException {

        System.out.println(customerRequestString);
        RegisterRequest registerRequest= new RegisterRequest();
        Customer newCustomer = newCustomer(customerRequestString);
        registerRequest.setCustomer(newCustomer);
        System.out.println(registerRequest.getCustomer().toString());

        return ResponseEntity.ok(customerService.registerCustomer(registerRequest.getCustomer()));
    }

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerLoginResponse> login (@RequestBody String loginRequestString) throws JSONException {

        CustomerLoginRequest customerLoginRequest = new CustomerLoginRequest();
        String name = convertJsonStringToObject(loginRequestString).get("name").toString();
        String password = convertJsonStringToObject(loginRequestString).get("password").toString();
        customerLoginRequest.setCustomerName(name);
        customerLoginRequest.setPassword(password);


        return ResponseEntity.ok(customerService.login(customerLoginRequest));
    }

    public static JSONObject convertJsonStringToObject(String jasonString) {
        try {
            return new JSONObject(jasonString);
        } catch (Exception e) {return null;}
    }

    public static Customer newCustomer(String jsonString) throws JSONException {

        JSONObject jsonObject = convertJsonStringToObject(jsonString);
        String name = jsonObject.get("name").toString();
        String password = jsonObject.get("password").toString();

        return new Customer(name, password);
    }


}
