package com.example.coffeeshop.controller.order;

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
class PlaceOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPlaceCorrectOrderShouldSucceed() throws Exception {
        mockMvc.perform(post("/coffeeshop/v1/customer/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\":\"Mbuso\",\n" +
                                "    \"description\":\"americano\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{" +
                        "\"message\":\"Success.\"," +
                        "\"success\":true" +
                        "}")));
    }

}