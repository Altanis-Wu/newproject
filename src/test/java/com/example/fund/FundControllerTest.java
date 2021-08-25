package com.example.fund;

import com.example.manager.Manager;
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

import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FundController.class)
class FundControllerTest {
    @MockBean
    FundRepository fundRepository;
    @MockBean
    FundService fundService;
    @Autowired
    MockMvc mockMvc;
    List<Fund> defaultFund = List.of(
            new Fund(0, "Tech", new Manager(), new HashSet<>()),
            new Fund(1, "Medi", new Manager(), new HashSet<>())
    );
    @Test
    void getFunds() throws Exception{
        when(fundService.getFunds()).thenReturn(defaultFund);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/security")).
                andExpect(status().isOk());
    }

    @Test
    void getFund() throws Exception {
        when(fundService.getFund(0)).thenReturn(defaultFund.get(0));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fund/0")).
                andExpect(status().isOk());
    }

    @Test
    void addFund() throws Exception {
        String json = "{\"fundId\": 3, \"name\": Car, " +
                "\"manager\": {\"employeeId\": 3, \"firstName\": \"Tom\", " +
                "+\"lastName\": \"Jerry\", \"funds\": []}, \"positions\" : []}";
        RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/fund").
                content(json).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk());
        verify(fundService).addNewFund(any(Fund.class));
    }

    @Test
    void deleteFund() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/fund/0")).
                andExpect(status().isOk());
        verify(fundService).deleteFund(0);
    }

    @Test
    void updateFund() throws Exception{
        String json = "{\"fundId\": 0, \"name\": Tech, " +
                "\"manager\": {\"employeeId\": 1, \"firstName\": \"Larry\", " +
                "\"lastName\": \"Wu\", \"funds\": []}, \"positions\" : []}";
        RequestBuilder request = MockMvcRequestBuilders.put("/api/v1/fund/0").
                content(json).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk());
        verify(fundService).updateFund(0, fundService.getFund(0));
    }
}