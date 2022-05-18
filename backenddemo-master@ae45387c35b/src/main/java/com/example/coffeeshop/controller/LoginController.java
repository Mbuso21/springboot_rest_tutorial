//package com.example.backenddemo.controller;
//
//import com.example.backenddemo.model.customer.*;
//import com.example.backenddemo.services.CustomerService;
//import org.springframework.boot.configurationprocessor.json.JSONException;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import static com.example.backenddemo.controller.CustomerController.convertJsonStringToObject;
//
//@RestController
//@RequestMapping(path="/coffeeshop/v1/customer")
//public class LoginController {
//    private final CustomerService customerService;
//
//    public LoginController(CustomerService customerService) {
//        this.customerService = customerService;
//    }
//
//    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<CustomerLoginResponse> login(@RequestBody String loginRequestString) throws JSONException {
//
//        CustomerLoginRequest customerLoginRequest = new CustomerLoginRequest();
//        String name = convertJsonStringToObject(loginRequestString).get("name").toString();
//        String password = convertJsonStringToObject(loginRequestString).get("password").toString();
//        customerLoginRequest.setCustomerName(name);
//        customerLoginRequest.setPassword(password);
//
//
//        return ResponseEntity.ok(customerService.login(customerLoginRequest));
//    }
//}