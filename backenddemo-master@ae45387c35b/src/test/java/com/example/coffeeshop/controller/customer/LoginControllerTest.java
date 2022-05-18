package com.example.coffeeshop.controller.customer;

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
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Login failed because customer isn't on the database")
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