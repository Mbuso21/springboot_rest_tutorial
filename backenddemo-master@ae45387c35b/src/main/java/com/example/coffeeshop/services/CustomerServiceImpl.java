package com.example.coffeeshop.services;

import com.example.coffeeshop.entities.CustomerEntity;
import com.example.coffeeshop.model.customer.Customer;
import com.example.coffeeshop.model.customer.CustomerRequest;
import com.example.coffeeshop.model.customer.CustomerResponse;
import com.example.coffeeshop.repos.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

        //todo: check for success and return the apropriate response


        response.setMessage("Success.");
        response.setSuccess(true);

        return response;
    }

    @Override
    public CustomerResponse login(CustomerRequest customerRequest) {

        CustomerEntity customer = customerRepo.findByNameEquals(customerRequest.getCustomerName());
        CustomerResponse response = new CustomerResponse();
        response.setSuccess(false);
        if(Objects.nonNull(customer))
        {

            if(customer.getPassword().equals(customerRequest.getPassword()))
            {
                response.setSuccess(true);
                response.setMessage("customer authenticated.");
                return  response;
            }


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
}
