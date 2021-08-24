package com.example.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ManagerController.class)
class ManagerControllerTest {
    @MockBean
    ManagerService managerService;
    @MockBean
    ManagerRepository managerRepository;
    @Autowired
    MockMvc mockMvc;
    List<Manager> defaultManagers = List.of(
            new Manager(1, "Larry", "Wu", new ArrayList<>()),
            new Manager(2, "Iris", "Ye", new ArrayList<>()));
    @Test
    void getManagers() throws Exception{
        when(managerService.getManagers()).thenReturn(defaultManagers);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/manager")).
                andExpect(status().isNotFound());
    }

    @Test
    void getManager() throws Exception{
        when(managerService.getManager(1)).thenReturn(defaultManagers.get(0));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/manager/1")).
                andExpect(status().isOk());
    }

    @Test
    void addManager() throws Exception{
        String json = "{\"employeeId\": 3, \"firstName\": \"Tom\", " +
                "\"lastName\": \"Jerry\", \"funds\": []}";
        RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/manager").
                content(json).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk());
        verify(managerService).addManager(any(Manager.class));
    }

    @Test
    void deleteManager() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/manager/1")).
                andExpect(status().isOk());
        verify(managerService).deleteManager(1);
    }

    @Test
    void updateManager() throws Exception{
        when(managerService.getManager(1)).thenReturn(defaultManagers.get(0));
        String json = "{\"employeeId\": 1, \"firstName\": \"Altanis\", " +
                "\"lastName\": \"Wu\", \"funds\": []}";
        RequestBuilder request = MockMvcRequestBuilders.put("/api/v1/manager/1").
                content(json).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk());
        verify(managerService).updateManager(1, managerService.getManager(1));
    }
}