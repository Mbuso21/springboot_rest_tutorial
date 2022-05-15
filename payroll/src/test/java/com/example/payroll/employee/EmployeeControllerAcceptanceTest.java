package com.example.payroll.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetAllEmployeeData() throws Exception {
        mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"_embedded\":" +
                                                                "{\"employeeList\":" +
                                                                        "[" +
                                                                            "{\"id\":1,\"firstName\":\"Bilbo\",\"lastName\":\"Baggins\",\"role\":\"burglar\",\"name\":\"Bilbo Baggins\",\"_links\":{\"self\":{\"href\":\"http://localhost/employees/1\"},\"employees\":{\"href\":\"http://localhost/employees\"}}},{\"id\":2,\"firstName\":\"Frodo\",\"lastName\":\"Baggins\",\"role\":\"thief\",\"name\":\"Frodo Baggins\",\"_links\":{\"self\":{\"href\":\"http://localhost/employees/2\"},\"employees\":{\"href\":\"http://localhost/employees\"}}}]},\"_links\":{\"self\":{\"href\":\"http://localhost/employees\"}}}")));
    }

    @Test
    void shouldGetOneEmployeeData() throws Exception {
        mockMvc.perform(get("/employees/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"firstName\":\"Bilbo\",\"lastName\":\"Baggins\",\"role\":\"burglar\",\"name\":\"Bilbo Baggins\",\"_links\":{\"self\":{\"href\":\"http://localhost/employees/1\"},\"employees\":{\"href\":\"http://localhost/employees\"}}}")));
    }

    @Test
    void shouldAddANewEmployee() throws Exception {
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Samwise Gamgee\",\n" +
                                "    \"role\": \"gardener\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(equalTo("{\"id\":5,\"firstName\":\"Samwise\",\"lastName\":\"Gamgee\",\"role\":\"gardener\",\"name\":\"Samwise Gamgee\",\"_links\":{\"self\":{\"href\":\"http://localhost/employees/5\"},\"employees\":{\"href\":\"http://localhost/employees\"}}}")));
    }

    @Test
    void () {
    }

    @Test
    void deleteEmployee() {
    }
}