package com.example.fund;

import com.example.manager.Manager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FundServiceTest {
    @Mock
    private FundRepository fundRepository;
    @InjectMocks
    private FundService fundService;
    List<Fund> defaultFund = List.of(
            new Fund(0, "Tech", new Manager(), new HashSet<>()),
            new Fund(1, "Medi", new Manager(), new HashSet<>())
    );
    @Test
    void getFunds() {
        when(fundRepository.findAll()).thenReturn(defaultFund);
        List<Fund> funds = fundService.getFunds();
        assertNotNull(funds);
        assertTrue(funds.size() > 0);
    }

    @Test
    void getFund() {
        when(fundRepository.findById(0)).thenReturn(Optional.of(defaultFund.get(0)));
        Fund fund = fundService.getFund(0);
        assertNotNull(fund);
    }

    @Test
    void addNewFund() {
        Fund fund = new Fund(2, "Car", new Manager(), new HashSet<>());
        fundService.addNewFund(fund);
        verify(fundRepository).save(fund);
    }

    @Test
    void deleteFund() {
        when(fundRepository.existsById(0)).thenReturn(true);
        fundService.deleteFund(0);
        verify(fundRepository).deleteById(0);
    }

    @Test
    void updateFund() {
        when(fundRepository.findById(0)).thenReturn(Optional.of(defaultFund.get(0)));
        Fund fund = fundService.getFund(0);
        fund.setName("Car");
        fundService.updateFund(0, fund);
        verify(fundRepository).save(fund);
    }
}