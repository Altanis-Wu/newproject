package com.example.position;

import com.example.security.Security;
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
@WebMvcTest(PositionController.class)
class PositionControllerTest {
    @MockBean
    PositionService positionService;
    @MockBean
    PositionRepository positionRepository;
    @Autowired
    MockMvc mockMvc;
    List<Position> defaultPositions = List.of(
            new Position(0), new Position(1)
    );
    @Test
    void getPositions() throws Exception{
        when(positionService.getPositions()).thenReturn(defaultPositions);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/position")).
                andExpect(status().isOk());
    }

    @Test
    void getPosition() throws Exception{
        when(positionService.getPosition(0)).thenReturn(defaultPositions.get(0));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/position/0")).
                andExpect(status().isOk());
    }

    @Test
    void addPosition() throws Exception{
        String json = "{\"positionId\": 3, \"security\": " +
                "{\"securityId\": 2, \"symbol\": \"AMD\", \"positions\": []}," +
                "\"quantity\": 1000, \"datePurchased\": 2021-08-09," +
                "\"fund\": {\"fundId\": 0, \"name\": Tech, \"manager\": " +
                "{\"employeeId\": 1, \"firstName\": \"Larry\", " +
                "\"lastName\": \"Wu\", \"funds\": []}, \"positions\" : []}}";
        RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/position").
                content(json).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk());
        verify(positionService).addPosition(any(Position.class));
    }

    @Test
    void deletePosition() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/position/0")).
                andExpect(status().isOk());
        verify(positionService).deletePosition(0);
    }

    @Test
    void updatePosition() throws Exception{
        String json = "{\"positionId\": 0, \"security\": " +
                "{\"securityId\": 2, \"symbol\": \"Microsoft\", \"positions\": []}," +
                "\"quantity\": 1000, \"datePurchased\": 2021-08-09," +
                "\"fund\": {\"fundId\": 0, \"name\": Tech, \"manager\": " +
                "{\"employeeId\": 1, \"firstName\": \"Larry\", " +
                "\"lastName\": \"Wu\", \"funds\": []}, \"positions\" : []}}";
        RequestBuilder request = MockMvcRequestBuilders.put("/api/v1/position/0").
                content(json).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk());
        verify(positionService).updatePosition(0, positionService.getPosition(0));
    }
}