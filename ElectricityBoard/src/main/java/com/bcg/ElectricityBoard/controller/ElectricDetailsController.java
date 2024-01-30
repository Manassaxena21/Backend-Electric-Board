package com.bcg.ElectricityBoard.controller;

import com.bcg.ElectricityBoard.exception.ResourceNotFoundException;
import com.bcg.ElectricityBoard.model.ElectricDetails;
import com.bcg.ElectricityBoard.service.ElectricConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Rest Controller defines class as controller with accepting restapi as body
@RequestMapping("/api/electricity-connections")
@CrossOrigin(origins = "*") //To enable CrossOrigin for React
public class
ElectricDetailsController {
    @Autowired
    private ElectricConnectionService electricConnectionService;

    @GetMapping("/home") // Api to get data on homepage
    public ResponseEntity<List<ElectricDetails>> getHomePageData() {
        List<ElectricDetails> connections = electricConnectionService.getAllConnections();
        return ResponseEntity.ok(connections);
    }
    @GetMapping("/{id}") //Api to get all Connections
    public ResponseEntity<ElectricDetails> getConnectionById(@PathVariable int id) {
        ElectricDetails connection = electricConnectionService.getConnectionById(id);
        return ResponseEntity.ok(connection);
    }

    @PutMapping("/update/{id}") // Api to update with id
    public ResponseEntity<ElectricDetails> updateConnection(
            @PathVariable int id,
            @RequestBody ElectricDetails updatedDetails) {
        try {
            ElectricDetails updatedConnection = electricConnectionService.updateConnection(updatedDetails);
            return ResponseEntity.ok(updatedConnection);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(updatedDetails);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Exception handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    // Exception handler for IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
