package com.example.coffeeshop.controller.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GetAllOrdersByNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void successfulGetOrdersByName() throws Exception {
        // Create orders for Mbuso
        mockMvc.perform(post("/coffeeshop/v1/customer/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\":\"Mbuso\",\n" +
                                "    \"description\":\"americano\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(equalTo("{" +
                        "\"message\":\"Success.\"," +
                        "\"success\":true" +
                        "}")));



        mockMvc.perform(get("/coffeeshop/v1/customer/order/Mbuso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Mbuso\",\n" +
                                "    \"password\": \"1234\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}