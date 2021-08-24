package com.example.fund;

import com.example.manager.Manager;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FundTest {

    @Test
    void getFundId() {
        Fund fund = new Fund(0, "Tech", new Manager());
        assertEquals(0, fund.getFundId());
    }

    @Test
    void setFundId() {
        Fund fund = new Fund(0, "Tech", new Manager());
        fund.setFundId(1);
        assertEquals(1, fund.getFundId());
    }

    @Test
    void getName() {
        Fund fund = new Fund(0, "Tech", new Manager());
        assertEquals("Tech", fund.getName());
    }

    @Test
    void setName() {
        Fund fund = new Fund(0, "Tech", new Manager());
        fund.setName("Medi");
        assertEquals("Medi", fund.getName());
    }

    @Test
    void getManager() {
        Fund fund = new Fund(0, "Tech", new Manager());
        assertNotNull(fund);
    }

    @Test
    void setManager() {
        Fund fund = new Fund(0, "Tech", new Manager());
        fund.setManager(new Manager(0, "Larry", "Wu"));
        assertEquals(0, fund.getManager().getEmployeeId());
    }

    @Test
    void getPositions() {
        Fund fund1 = new Fund(0, "tech", new Manager());
        assertNull(fund1.getPositions());
        Fund fund2 = new Fund(1, "Medi", new Manager(), new HashSet<>());
        assertNotNull(fund2.getPositions());
    }

    @Test
    void setPositions() {
        Fund fund = new Fund(0, "tech", new Manager());
        fund.setPositions(new HashSet<>());
        assertNotNull(fund.getPositions());
    }
}