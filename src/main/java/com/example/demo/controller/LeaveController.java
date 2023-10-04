package com.example.demo.controller;

import com.example.demo.entity.Leave;
import com.example.demo.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(tags = {"Leave"})
@RestController
@RequestMapping("/leaves")
public class LeaveController {
    private final LeaveService leaveService;

    @Autowired
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @ApiOperation(value = "Get all leave records")
    @GetMapping
    public ResponseEntity<List<Leave>> getAllLeaves() {
        List<Leave> leaves = leaveService.getAllLeaves();
        return new ResponseEntity<>(leaves, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new leave record")
    @PostMapping
    public ResponseEntity<Leave> addLeave(@RequestBody Leave leave) {
        Leave savedLeave = leaveService.addLeave(leave);
        return ResponseEntity.ok(savedLeave);
    }

    @ApiOperation(value = "Update an existing leave record")
    @PutMapping("/{id}")
    public ResponseEntity<Leave> updateLeave(@PathVariable Long id, @RequestBody Leave updatedLeave) {
        Leave updated = leaveService.updateLeave(id, updatedLeave);
        return ResponseEntity.ok(updated);
    }

    // Delete a leave
    @ApiOperation(value = "Delete a leave record by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return ResponseEntity.noContent().build();
    }

}
