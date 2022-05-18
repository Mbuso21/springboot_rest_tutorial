package com.example.coffeeshop.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class RegisterControllerTest {

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

        // Register Sammy should also succeed
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
    @DisplayName("Register with no name should fail")
    void failedCustomerRegistrationNoName() throws Exception {
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
    @DisplayName("Register with no password should fail")
    void failedCustomerRegistrationNoPassword() throws Exception {
        mockMvc.perform(post("/coffeeshop/v1/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Rusty\",\n" +
                                "    \"password\": \"\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{" +
                        "\"message\":\"Failed. Details are incorrect\"," +
                        "\"success\":false" +
                        "}")));
    }

    @Test
    @DisplayName("Register twice same details should fail")
    void failedCustomerRegistrationDuplicateRegistration() throws Exception {
        // Registration should succeed for Franco
        mockMvc.perform(post("/coffeeshop/v1/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Franco\",\n" +
                                "    \"password\": \"1234\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"message\":\"Success.\",\"success\":true}")));

        // Try registering Franco again should fail
        mockMvc.perform(post("/coffeeshop/v1/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Franco\",\n" +
                                "    \"password\": \"1234\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{" +
                        "\"message\":" +
                                    "\"Failed. Customer already registered\"," +
                        "\"success\"" +
                                    ":false}")));
    }


}