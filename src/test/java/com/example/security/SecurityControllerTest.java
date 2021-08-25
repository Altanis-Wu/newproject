package com.example.security;

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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SecurityController.class)
class SecurityControllerTest {
    @MockBean
    SecurityService securityService;
    @MockBean
    SecurityRepository securityRepository;
    @Autowired
    MockMvc mockMvc;
    List<Security> defaultSecurity = List.of(
            new Security(0, "IBM"),
            new Security(1, "Apple")
    );
    @Test
    void getSecurities() throws Exception{
        when(securityService.getSecurities()).thenReturn(defaultSecurity);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/security")).
                andExpect(status().isOk());
    }

    @Test
    void getSecurity() throws Exception{
        when(securityService.getSecurity(0)).thenReturn(defaultSecurity.get(0));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/security")).
                andExpect(status().isOk());
    }

    @Test
    void addSecurity() throws Exception{
        String json = "{\"securityId\": 2, \"symbol\": \"AMD\", \"positions\": []}";
        RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/security").
                content(json).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk());
        verify(securityService).addNewSecurity(any(Security.class));
    }

    @Test
    void deleteSecurity() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/security/0")).
                andExpect(status().isOk());
        verify(securityService).deleteSecurity(0);
    }

    @Test
    void updateSecurity() throws Exception{
        String json = "{\"securityId\": 0, \"symbol\": \"Microsoft\", \"positions\": []}";
        RequestBuilder request = MockMvcRequestBuilders.put("/api/v1/security/0").
                content(json).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk());
        verify(securityService).updateSecurity(0, securityService.getSecurity(0));
    }
}