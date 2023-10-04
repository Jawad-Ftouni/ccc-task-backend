package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "leave_type")
    @JsonProperty("LeaveTypeId")
    private LeaveType leaveType;

    @Column(name = "start_date")
    @JsonProperty("start_date")
    private Date start_date;

    @Column(name = "end_date")
    @JsonProperty("end_date")
    private Date end_date;

    @Column(name = "number_of_days")
    @JsonProperty("number_of_days")
    private int numberOfDays;

    @Column(name = "note")
    @JsonProperty("note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonProperty("employee_id")
    private Employee employee;
    // Constructors, getters, setters, and other methods


    public void setId(Long id) {
        this.id = id;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

}
