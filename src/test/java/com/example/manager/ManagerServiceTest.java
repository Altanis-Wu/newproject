package com.example.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManagerServiceTest{
    @Mock
    private ManagerRepository managerRepository;

    @InjectMocks
    private ManagerService managerService;

    List<Manager> defaultManager = List.of(
            new Manager(1, "Larry", "Wu", new ArrayList<>()),
            new Manager(2, "Iris", "Ye", new ArrayList<>()));
    @Test
    void getManagers() {
        when(managerRepository.findAll()).thenReturn(defaultManager);
        List<Manager> managers = managerService.getManagers();
        assertNotNull(managers);
        assertTrue(managers.size() > 0);
    }

    @Test
    void getManager() {
        when(managerRepository.findById(1)).thenReturn(Optional.of(defaultManager.get(0)));
        Manager manager = managerService.getManager(1);
        assertNotNull(manager);
    }

    @Test
    void addManager() {
        Manager manager = new Manager(3, "Tom", "Jerry", new ArrayList<>());
        managerService.addManager(manager);
        verify(managerRepository).save(manager);
    }

    @Test
    void deleteManager() {
        when(managerRepository.existsById(1)).thenReturn(true);
        managerService.deleteManager(1);
        verify(managerRepository).deleteById(1);
    }

    @Test
    void updateManager() {
        when(managerRepository.findById(1)).thenReturn(Optional.of(defaultManager.get(0)));
        Manager manager = managerService.getManager(1);
        manager.setFirstName("Altanis");
        managerService.updateManager(1, manager);
        verify(managerRepository).save(manager);
    }
}