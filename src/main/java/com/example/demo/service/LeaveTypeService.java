package com.example.demo.service;

import com.example.demo.entity.LeaveType;
import com.example.demo.repository.LeaveTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LeaveTypeService {
    private final LeaveTypeRepository leaveTypeRepository;

    @Autowired
    public LeaveTypeService(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    public List<LeaveType> getLeaveTypes() {
        return leaveTypeRepository.findAll();
    }

    public LeaveType addLeaveType(LeaveType leaveType) {
        // Implement the logic to save the leaveType using leaveTypeRepository.save()
        return leaveTypeRepository.save(leaveType);
    }

    public LeaveType updateLeaveType(Long id, LeaveType updatedLeaveType) {
        LeaveType existingLeaveType = leaveTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LeaveType not found with id: " + id));

        // Update the fields of the existing LeaveType with values from updatedLeaveType
        existingLeaveType.setName(updatedLeaveType.getName());

        return leaveTypeRepository.save(existingLeaveType);
    }

    public void deleteLeaveType(Long id) {
        LeaveType leaveType = leaveTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LeaveType not found with id: " + id));

        leaveTypeRepository.delete(leaveType);
    }


}
