package com.example.coffeeshop.services;

import com.example.coffeeshop.entities.CustomerEntity;
import com.example.coffeeshop.model.customer.Customer;
import com.example.coffeeshop.model.customer.CustomerRequest;
import com.example.coffeeshop.model.customer.CustomerResponse;
import com.example.coffeeshop.repos.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public CustomerResponse registerCustomer(Customer customer) {

        CustomerResponse response = new CustomerResponse();
        if(!checkCustomerDetailsCorrect(customer)) {
            response.setMessage("Failed. Details are incorrect");
            response.setSuccess(false);
            return response;
        }

        if(checkIfCustomerExists(customer)) {
            response.setMessage("Failed. Customer already registered");
            response.setSuccess(false);
            return response;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        BeanUtils.copyProperties(customer,customerEntity);
        CustomerEntity dbResponse =  customerRepo.save(customerEntity);
        log.info("Db reponse -> {}", dbResponse);


        response.setMessage("Success.");
        response.setSuccess(true);

        return response;
    }

    @Override
    public CustomerResponse login(CustomerRequest customerRequest) {

        CustomerEntity customer = customerRepo.findByNameEquals(customerRequest.getCustomerName());
        CustomerResponse response = new CustomerResponse();
        response.setSuccess(false);
        if(Objects.nonNull(customer) && customer.getPassword().equals(customerRequest.getPassword()))
        {
            response.setSuccess(true);
            response.setMessage("customer authenticated.");
            return  response;
        }
        response.setMessage("failed to authenticate customer");

        return response;
    }

    public boolean checkCustomerDetailsCorrect(Customer customer) {
        if(customer.getName().length() == 0)return false;
        if(customer.getPassword().length() < 4) return false;
        return true;
    }

    public boolean checkIfCustomerExists(Customer customer) {
        if(customerRepo.findByNameEquals(customer.getName()) == null) return false;
        return customer.getName().equals(customerRepo.findByNameEquals(customer.getName()).getName());
    }

    @Override
    public HttpStatus setHttpStatus(CustomerResponse response, String endPoint) {
        if(endPoint.equals("register") && response.isSuccess()) {
            return HttpStatus.CREATED;
        }
        if(response.isSuccess()) return HttpStatus.OK;
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public JSONObject convertJsonStringToObject(String jasonString) {
        try {
            return new JSONObject(jasonString);
        } catch (Exception e) {return null;}
    }

    @Override
    public Customer newCustomer(String jsonString) throws JSONException {

        JSONObject jsonObject = convertJsonStringToObject(jsonString);
        String name = jsonObject.get("name").toString();
        String password = jsonObject.get("password").toString();

        return new Customer(name, password);
    }


}
