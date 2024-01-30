package com.bcg.ElectricityBoard.service;

import com.bcg.ElectricityBoard.exception.ResourceNotFoundException;
import com.bcg.ElectricityBoard.model.ElectricDetails;
import com.bcg.ElectricityBoard.repository.ElectricDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ElectricConnectionServiceTest {

    @Mock
    private ElectricDetailsRepository electricDetailsRepository;

    @InjectMocks
    private ElectricConnectionService electricConnectionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllConnections() {
        // Mock data
        List<ElectricDetails> electricDetailsList = new ArrayList<>();
        when(electricDetailsRepository.findAll()).thenReturn(electricDetailsList);

        // Test the service method
        List<ElectricDetails> result = electricConnectionService.getAllConnections();

        // Verify the result
        assertEquals(electricDetailsList, result);
        verify(electricDetailsRepository, times(1)).findAll();
    }

    @Test
    void testGetConnectionById() {
        // Mock data
        long id = 1L;
        ElectricDetails electricDetails = new ElectricDetails();
        electricDetails.setId((int) id);
        when(electricDetailsRepository.findById(id)).thenReturn(Optional.of(electricDetails));

        // Test the service method
        ElectricDetails result = electricConnectionService.getConnectionById((int) id);

        // Verify the result
        assertEquals(electricDetails, result);
        verify(electricDetailsRepository, times(1)).findById(id);
    }

    @Test
    void testGetConnectionByIdNotFound() {
        // Mock data
        long id = 1L;
        when(electricDetailsRepository.findById(id)).thenReturn(Optional.empty());

        // Test the service method and expect ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> electricConnectionService.getConnectionById((int) id));

        // Verify that findById was called
        verify(electricDetailsRepository, times(1)).findById(id);
    }

    @Test
    void testUpdateConnection() {
        // Mock data
        ElectricDetails existingDetails = new ElectricDetails();
        existingDetails.setId(1);
        existingDetails.setLoadApplied(1500.0);
        existingDetails.setIdNumber(1L);

        ElectricDetails updatedDetails = new ElectricDetails();
        updatedDetails.setId(1);
        updatedDetails.setLoadApplied(1000.0);

        when(electricDetailsRepository.findById(1L)).thenReturn(Optional.of(existingDetails));
        when(electricDetailsRepository.save(updatedDetails)).thenReturn(updatedDetails);

        // Test the service method
        ElectricDetails result = electricConnectionService.updateConnection(updatedDetails);

        // Verify the result
        assertEquals(updatedDetails, result);
        assertEquals(1000, result.getLoadApplied());
        verify(electricDetailsRepository, times(1)).findById(1L);
        verify(electricDetailsRepository, times(1)).save(updatedDetails);
    }

    @Test
    void testUpdateConnectionWithInvalidLoadApplied() {
        // Mock data
        ElectricDetails existingDetails = new ElectricDetails();
        existingDetails.setId(1);
        existingDetails.setLoadApplied(1500.0);

        ElectricDetails updatedDetails = new ElectricDetails();
        updatedDetails.setId(1);
        updatedDetails.setLoadApplied(2500.0);

        when(electricDetailsRepository.findById(1L)).thenReturn(Optional.of(existingDetails));

        // Test the service method and expect IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> electricConnectionService.updateConnection(updatedDetails));

        // Verify that findById was called
        verify(electricDetailsRepository, times(1)).findById(1L);
        // Verify that save was not called
        verify(electricDetailsRepository, never()).save(updatedDetails);
    }
}
