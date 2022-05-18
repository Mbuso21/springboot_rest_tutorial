//package com.example.backenddemo.controller;
//
//import com.example.backenddemo.model.customer.Customer;
//import com.example.backenddemo.model.customer.RegisterRequest;
//import com.example.backenddemo.model.customer.RegisterResponse;
//import com.example.backenddemo.services.CustomerService;
//import org.springframework.boot.configurationprocessor.json.JSONException;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(path="/coffeeshop/v1/customer")
//public class RegisterController {
//    private final CustomerService customerService;
//
//    public RegisterController(CustomerService customerService) {
//        this.customerService = customerService;
//    }
//
//    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<RegisterResponse> registerCustomer(@RequestBody String customerRequestString) throws JSONException {
//
//        System.out.println(customerRequestString);
//        RegisterRequest registerRequest = new RegisterRequest();
//        Customer newCustomer = CustomerController.newCustomer(customerRequestString);
//        registerRequest.setCustomer(newCustomer);
//        System.out.println(registerRequest.getCustomer().toString());
//
//        return ResponseEntity.ok(customerService.registerCustomer(registerRequest.getCustomer()));
//    }
//}
