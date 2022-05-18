package com.example.coffeeshop.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void successfulCustomerRegistration() throws Exception {
        mockMvc.perform(post("/coffeeshop/v1/customer/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "    \"name\": \"Mbuso\",\n" +
                            "    \"password\": \"1234\"\n" +
                            "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{" +
                        "\"message\":\"Success.\"," +
                        "\"success\":true" +
                        "}")));
    }

    @Test
    @DisplayName("Register Multiple Customers")
    void successfulCustomerRegistrationForMoreThanOneCustomer() throws Exception {
        // Register Mbuso should succeed
        mockMvc.perform(post("/coffeeshop/v1/customer/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "    \"name\": \"Kelly\",\n" +
                            "    \"password\": \"1234\"\n" +
                            "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{" +
                        "\"message\":\"Success.\"," +
                        "\"success\":true" +
                        "}")));

        // Register NoMbuso should also succeed
        mockMvc.perform(post("/coffeeshop/v1/customer/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "    \"name\": \"Sammy\",\n" +
                            "    \"password\": \"1234\"\n" +
                            "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{" +
                        "\"message\":\"Success.\"," +
                        "\"success\":true" +
                        "}")));
    }

    @Test
    void failedCustomerRegistration() throws Exception {
        mockMvc.perform(post("/coffeeshop/v1/customer/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "    \"name\": \"\",\n" +
                            "    \"password\": \"1234\"\n" +
                            "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{" +
                        "\"message\":\"Failed. Details are incorrect\"," +
                        "\"success\":false" +
                        "}")));
    }

    @Test
    void failedCustomerLogin() throws Exception {
        mockMvc.perform(post("/coffeeshop/v1/customer/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "    \"name\": \"Hlubi\",\n" +
                            "    \"password\": \"1234\"\n" +
                            "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{" +
                        "\"message\":\"failed to authenticate customer\"," +
                        "\"success\":false" +
                        "}")));
    }

    @Test
    @DisplayName("Register the same customer twice")
    void failedCustomerRegistrationRegisterSameDetailsTwice() throws Exception {
        // Register Customer first should succeed.
        mockMvc.perform(post("/coffeeshop/v1/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Nkosi\",\n" +
                                "    \"password\": \"1234\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"message\":\"Success.\",\"success\":true}")));

        // Register same Customer again should fail
        mockMvc.perform(post("/coffeeshop/v1/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Nkosi\",\n" +
                                "    \"password\": \"1234\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(
                        "{\"message\":" +
                                "\"Failed. Customer already registered\"," +
                                "\"success\":false}")));
    }

    @Test
    @DisplayName("Login Registered customer")
    void successfulCustomerLogin() throws Exception {
        // Register Customer
        mockMvc.perform(post("/coffeeshop/v1/customer/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "    \"name\": \"Nombuso\",\n" +
                            "    \"password\": \"1234\"\n" +
                            "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"message\":\"Success.\",\"success\":true}")));

        // Login Customer
        mockMvc.perform(post("/coffeeshop/v1/customer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Nombuso\",\n" +
                                "    \"password\": \"1234\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{" +
                        "\"message\":\"customer authenticated.\"," +
                        "\"success\":true}")));
    }
}