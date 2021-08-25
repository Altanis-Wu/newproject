package com.example.position;

import com.example.fund.Fund;
import com.example.security.Security;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void getPositionId() {
        Position position = new Position(0);
        assertEquals(0, position.getPositionId());
    }

    @Test
    void setPositionId() {
        Position position = new Position();
        position.setPositionId(3);
        assertEquals(3, position.getPositionId());
    }

    @Test
    void getSecurity() {
        Position position1 = new Position();
        assertNull(position1.getSecurity());
        Position position2 = new Position(new Security(), 1000, LocalDate.now(), new Fund());
        assertNotNull(position2.getSecurity());
    }

    @Test
    void setSecurity() {
        Position position = new Position();
        position.setSecurity(new Security());
        assertNotNull(position.getSecurity());
    }

    @Test
    void getQuantity() {
        Position position = new Position(new Security(), 1000, LocalDate.now(), new Fund());
        assertEquals(1000, position.getQuantity());
    }

    @Test
    void setQuantity() {
        Position position = new Position();
        position.setQuantity(1000);
        assertEquals(1000, position.getQuantity());
    }

    @Test
    void getDatePurchased() {
        Position position1 = new Position();
        assertNull(position1.getDatePurchased());
        Position position2 = new Position(new Security(), 1000, LocalDate.now(), new Fund());
        assertNotNull(position2.getDatePurchased());
    }

    @Test
    void setDatePurchased() {
        Position position = new Position();
        position.setDatePurchased(LocalDate.now());
        assertNotNull(position.getDatePurchased());
    }

    @Test
    void getFund() {
        Position position1 = new Position();
        assertNull(position1.getFund());
        Position position2 = new Position(new Security(), 1000, LocalDate.now(), new Fund());
        assertNotNull(position2.getFund());
    }

    @Test
    void setFund() {
        Position position = new Position();
        position.setFund(new Fund());
        assertNotNull(position.getFund());
    }
}