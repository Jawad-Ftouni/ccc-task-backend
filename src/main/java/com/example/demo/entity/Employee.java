package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @JsonProperty("name")
    @Column(name = "name")
    private String name;
    @Getter
    @JsonProperty("email")
    @Column(name = "email")
    private String email;
    @Getter
    @JsonProperty("address")
    @Column(name = "address")
    private String address;

    @Getter
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonProperty("department_id")
    private Department department;

    @Getter
    @OneToMany(mappedBy = "employee")
    @JsonProperty("leaves")
    private List<Leave> leaves;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setLeaves(List<Leave> leaves) {
        this.leaves = leaves;
    }
}