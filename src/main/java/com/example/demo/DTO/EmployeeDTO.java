package com.example.demo.DTO;

import java.util.List;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private List<LeaveDTO> leaves;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<LeaveDTO> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<LeaveDTO> leaves) {
        this.leaves = leaves;
    }
// Getter and setter methods
}
