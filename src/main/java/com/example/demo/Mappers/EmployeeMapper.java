package com.example.demo.Mappers;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.DTO.LeaveDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class EmployeeMapper {

    private final LeaveMapper leaveMapper; // Inject LeaveMapper here

    @Autowired
    public EmployeeMapper(LeaveMapper leaveMapper) {
        this.leaveMapper = leaveMapper;
    }

    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setAddress(employee.getAddress());

        // Set the leaves in the DTO using the LeaveMapper
        employeeDTO.setLeaves(employee.getLeaves().stream().map(leaveMapper::convertToLeaveDTO).collect(Collectors.toList()));

        return employeeDTO;
    }
}
