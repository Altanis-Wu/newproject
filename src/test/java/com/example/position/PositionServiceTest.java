package com.example.position;

import com.example.fund.Fund;
import com.example.security.Security;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PositionServiceTest {
    @Mock
    private PositionRepository positionRepository;
    @InjectMocks
    private PositionService positionService;
    List<Position> defaultPositions = List.of(
            new Position(0), new Position(1)
    );
    @Test
    void getPositions() {
        when(positionRepository.findAll()).thenReturn(defaultPositions);
        assertNotNull(positionService.getPositions());
        assertTrue(positionService.getPositions().size() > 0);
    }

    @Test
    void getPosition() {
        when(positionRepository.findById(0)).thenReturn(Optional.of(defaultPositions.get(0)));
        assertNotNull(positionService.getPosition(0));
    }

    @Test
    void addPosition() {
        Position position = new Position(2);
        positionService.addPosition(position);
        verify(positionRepository).save(position);
    }

    @Test
    void deletePosition() {
        when(positionRepository.existsById(0)).thenReturn(true);
        positionService.deletePosition(0);
        verify(positionRepository).deleteById(0);
    }

    @Test
    void updatePosition() {
        when(positionRepository.findById(0)).thenReturn(Optional.of(defaultPositions.get(0)));
        Position position = positionService.getPosition(0);
        position.setFund(new Fund());
        positionService.updatePosition(0, position);
        verify(positionRepository).save(position);
    }
}