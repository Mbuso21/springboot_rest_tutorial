package com.example.coffeeshop.controller.customer;


import com.example.coffeeshop.model.customer.*;
import com.example.coffeeshop.services.CustomerService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/coffeeshop/v1/customer")
public class ControllerMethods {
    private final CustomerService customerService;

    public ControllerMethods(CustomerService customerService) {
        this.customerService = customerService;
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
