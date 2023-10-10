package com.example.demo.Mappers;

import com.example.demo.DTO.LeaveDTO;
import com.example.demo.entity.Leave;
import org.springframework.stereotype.Component;

@Component
public class LeaveMapper {

    public LeaveDTO convertToLeaveDTO(Leave leave) {
        LeaveDTO leaveDTO = new LeaveDTO();
        leaveDTO.setId(leave.getId());
        leaveDTO.setStartDate(leave.getStart_date());
        leaveDTO.setEndDate(leave.getEnd_date());
        leaveDTO.setNumberOfDays(leave.getNumberOfDays());
        leaveDTO.setNote(leave.getNote());
        // Set other properties of LeaveDTO as needed

        return leaveDTO;
    }

}
