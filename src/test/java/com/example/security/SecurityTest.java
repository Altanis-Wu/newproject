package com.example.security;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SecurityTest {

    @Test
    void getSecurityId() {
        Security security = new Security(0, "IBM");
        assertEquals(0, security.getSecurityId());
    }

    @Test
    void setSecurityId() {
        Security security = new Security(0, "IBM");
        security.setSecurityId(1);
        assertEquals(1, security.getSecurityId());
    }

    @Test
    void getSymbol() {
        Security security = new Security(0, "IBM");
        assertEquals("IBM", security.getSymbol());
    }

    @Test
    void setSymbol() {
        Security security = new Security(0, "IBM");
        security.setSymbol("Apple");
        assertEquals("Apple", security.getSymbol());
    }

    @Test
    void getPositions() {
        Security security = new Security(0, "IBM");
        assertNull(security.getPositions());
    }

    @Test
    void setPositions() {
        Security security = new Security(0, "IBM");
        security.setPositions(new HashSet<>());
        assertNotNull(security.getPositions());
    }
}