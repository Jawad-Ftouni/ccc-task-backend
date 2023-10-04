package com.example.demo.controller;

import com.example.demo.entity.LeaveType;
import com.example.demo.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;

@Api(tags = {"LeaveType"})
@RestController
@RequestMapping("/leave-types")
public class LeaveTypeController {

    private final LeaveTypeService leaveTypeService;

    @Autowired
    public LeaveTypeController(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
    }

    @ApiOperation(value = "Create a new LeaveType")
    @PostMapping()
    public ResponseEntity<LeaveType> addLeaveType(@RequestBody LeaveType leaveType) {
        // Ensure leaveType is not null
        if (leaveType != null) {
            LeaveType savedLeaveType = leaveTypeService.addLeaveType(leaveType);
            return ResponseEntity.ok(savedLeaveType);
        } else {
            // Handle invalid request
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation(value = "get leave types")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success")
    })
    @GetMapping()
    public ResponseEntity<List<LeaveType>> getLeaveTypes() {
        List<LeaveType> leaveTypes = leaveTypeService.getLeaveTypes();
        return new ResponseEntity<>(leaveTypes, HttpStatus.OK);
    }

}
