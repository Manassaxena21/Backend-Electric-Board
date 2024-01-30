package com.bcg.ElectricityBoard.controller;

import com.bcg.ElectricityBoard.model.ElectricDetails;
import com.bcg.ElectricityBoard.service.ElectricConnectionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class ElectricDetailsControllerTest {

    @Mock
    private ElectricConnectionService electricConnectionService;

    @InjectMocks
    private ElectricDetailsController electricDetailsController;

    private MockMvc mockMvc;

    public ElectricDetailsControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = standaloneSetup(electricDetailsController).build();
    }

    @Test
    void testGetHomePageData() throws Exception {
        // Mock data
        List<ElectricDetails> connections = Arrays.asList(new ElectricDetails(), new ElectricDetails());
        when(electricConnectionService.getAllConnections()).thenReturn(connections);

        // Perform the MVC request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/electricity-connections/home"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(connections.size()));

        // Verify that getAllConnections was called
        verify(electricConnectionService, times(1)).getAllConnections();
    }

    @Test
    void testGetConnectionById() throws Exception {
        // Mock data
        int id = 1;
        ElectricDetails connection = new ElectricDetails();
        when(electricConnectionService.getConnectionById(id)).thenReturn(connection);

        // Perform the MVC request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/electricity-connections/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that getConnectionById was called
        verify(electricConnectionService, times(1)).getConnectionById(id);
    }

    @Test
    void testUpdateConnection() throws Exception {
        // Mock data
        int id = 1;
        ElectricDetails updatedDetails = new ElectricDetails();
        updatedDetails.setId(id);
        when(electricConnectionService.updateConnection(any())).thenReturn(updatedDetails);

        // Perform the MVC request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.put("/api/electricity-connections/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that updateConnection was called
        verify(electricConnectionService, times(1)).updateConnection(any());
    }
}
