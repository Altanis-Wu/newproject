package com.example.manager;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    @Test
    void getEmployeeId() {
        Manager manager1 = new Manager(0, "Tom", "Yang");
        Manager manager2 = new Manager(1, "Jerry", "Ye", new ArrayList<>());
        assertEquals(0, manager1.getEmployeeId());
        assertEquals(1, manager2.getEmployeeId());
    }

    @Test
    void setEmployeeId() {
        Manager manager = new Manager();
        manager.setEmployeeId(1);
        assertEquals(1, manager.getEmployeeId());
    }

    @Test
    void getFirstName() {
        Manager manager1 = new Manager(0, "Tom", "Yang");
        Manager manager2 = new Manager(1, "Jerry", "Ye", new ArrayList<>());
        assertEquals(manager1.getFirstName(),"Tom");
        assertEquals(manager2.getFirstName(), "Jerry");
    }

    @Test
    void setFirstName() {
        Manager manager = new Manager();
        manager.setFirstName("Tom");
        assertEquals(manager.getFirstName(),"Tom");
    }

    @Test
    void getLastName() {
        Manager manager1=new Manager(0, "Tom", "Yang");
        Manager manager2=new Manager(1, "Jerry", "Ye", new ArrayList<>());
        assertEquals(manager1.getLastName(), "Yang");
        assertEquals(manager2.getLastName(), "Ye");
    }

    @Test
    void setLastName() {
        Manager manager = new Manager();
        manager.setLastName("Ye");
        assertEquals(manager.getLastName(), "Ye");
    }

    @Test
    void getFunds() {
        Manager manager = new Manager();
        Manager manager2=new Manager(1, "Jerry", "Ye", new ArrayList<>());
        assertNull(manager.getFunds());
        assertEquals(0, manager2.getFunds().size());
    }

    @Test
    void setFunds() {
        Manager manager = new Manager();
        manager.setFunds(new ArrayList<>());
        assertEquals(0, manager.getFunds().size());
    }
}