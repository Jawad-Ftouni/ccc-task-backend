package com.example.demo.service;

import com.example.demo.entity.Leave;
import com.example.demo.repository.LeaveRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {
    private final LeaveRepository leaveRepository;

    @Autowired
    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public Leave addLeave(Leave leave) {
        // Implement validation or business logic as needed
        return leaveRepository.save(leave);
    }

    public Leave updateLeave(Long id, Leave updatedLeave) {
        Leave existingLeave = leaveRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leave not found with id: " + id));

        // Update the fields of the existing Leave with values from updatedLeave
        existingLeave.setLeaveType(updatedLeave.getLeaveType());
        existingLeave.setStart_date(updatedLeave.getStart_date());
        existingLeave.setEnd_date(updatedLeave.getEnd_date());
        existingLeave.setNumberOfDays(updatedLeave.getNumberOfDays());
        existingLeave.setNote(updatedLeave.getNote());
        existingLeave.setEmployee(updatedLeave.getEmployee());

        return leaveRepository.save(existingLeave);
    }

    public void deleteLeave(Long id) {
        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leave not found with id: " + id));

        leaveRepository.delete(leave);
    }

    public Optional<Leave> getLeaveById(Long id) {
         Optional<Leave> leave = this.leaveRepository.findById(id);
         if (leave.isPresent()){
             return leave;
         }
        return null;
    }
}
