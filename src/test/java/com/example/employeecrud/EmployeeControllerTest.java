package com.example.employeecrud;

import com.example.employeecrud.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository repository;

    @Test
    void listPageLoads() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees/list"));
    }

    @Test
    void createEmployeeRedirectsToList() throws Exception {
        long before = repository.count();
        mockMvc.perform(post("/employees/save")
                        .param("firstName", "Test")
                        .param("lastName", "User")
                        .param("email", "test.user@example.com")
                        .param("department", "Finance")
                        .param("salary", "50000")
                        .param("joiningDate", "2024-01-15"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees"));
        assertEquals(before + 1, repository.count());
    }

    @Test
    void invalidFormShowsErrors() throws Exception {
        mockMvc.perform(post("/employees/save").param("firstName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("employees/form"))
                .andExpect(model().attributeHasFieldErrors("employee", "firstName", "email"));
    }

    @Test
    void unknownEmployeeReturns404() throws Exception {
        mockMvc.perform(get("/employees/9999"))
                .andExpect(status().isNotFound());
    }
}
