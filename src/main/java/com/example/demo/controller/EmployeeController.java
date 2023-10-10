package com.example.demo.controller;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.Mappers.EmployeeMapper;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Leave;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags = {"Employee"})
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final LeaveService leaveService;
    private final EmployeeMapper employeeMapper;
    @Autowired
    public EmployeeController(EmployeeService employeeService, LeaveService leaveService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.leaveService = leaveService;
        this.employeeMapper = employeeMapper;
    }

    @ApiOperation(value = "Get all employees")
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        // Map the list of Employee objects to EmployeeDTO objects
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(employeeMapper::convertToDTO) // Assuming you have an employeeMapper instance
                .collect(Collectors.toList());

        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }


    @ApiOperation(value = "Create a new employee")
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    // Update an employee
    @ApiOperation(value = "Update an existing employee")
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Employee updated = employeeService.updateEmployee(id, updatedEmployee);
        return ResponseEntity.ok(updated);
    }

    // Delete an employee
    @ApiOperation(value = "Delete an employee by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "add leave to employee")
    @PutMapping("{employeeId}/leaves/{leaveId}")
    public ResponseEntity<EmployeeDTO> addLeaveToEmployee(@PathVariable Long employeeId, @PathVariable Long leaveId) {
        Optional<Employee> employeeOptional = employeeService.getEmployeeById(employeeId);
        Optional<Leave> leaveOptional = leaveService.getLeaveById(leaveId);

        if (employeeOptional.isPresent() && leaveOptional.isPresent()) {
            Leave leave = leaveOptional.get();
            Employee employee = employeeOptional.get();

            // Associate the leave with the employee
            leave.setEmployee(employee);
            employee.getLeaves().add(leave);

            // Update the leave and employee in the database
            leaveService.updateLeave(leaveId, leave);
            employeeService.updateEmployee(employeeId, employee);

            // Convert the employee to a DTO and return it in the response
            EmployeeDTO employeeDTO = employeeMapper.convertToDTO(employee); // Use your EmployeeMapper
            return ResponseEntity.ok(employeeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
