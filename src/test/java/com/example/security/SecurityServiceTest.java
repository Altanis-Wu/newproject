package com.example.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class SecurityServiceTest {
    @Mock
    private SecurityRepository securityRepository;
    @InjectMocks
    private SecurityService securityService;
    List<Security> defaultSecurity = List.of(
            new Security(0, "IBM"),
            new Security(1, "Apple")
    );

    @Test
    void getSecurities() {
        when(securityRepository.findAll()).thenReturn(defaultSecurity);
        assertNotNull(securityService.getSecurities());
        assertTrue(securityService.getSecurities().size() > 0);
    }

    @Test
    void getSecurity() {
        when(securityRepository.findById(0)).thenReturn(Optional.of(defaultSecurity.get(0)));
        assertNotNull(securityService.getSecurity(0));
    }

    @Test
    void addNewSecurity() {
        Security security = new Security(2, "AMD");
        securityService.addNewSecurity(security);
        verify(securityRepository).save(security);
    }

    @Test
    void deleteSecurity() {
        when(securityRepository.existsById(0)).thenReturn(true);
        securityService.deleteSecurity(0);
        verify(securityRepository).deleteById(0);
    }

    @Test
    void updateSecurity() {
        when(securityRepository.findById(0)).thenReturn(Optional.of(defaultSecurity.get(0)));
        Security security = securityService.getSecurity(0);
        security.setPositions(new HashSet<>());
        securityService.updateSecurity(0, security);
        verify(securityRepository).save(security);
    }
}