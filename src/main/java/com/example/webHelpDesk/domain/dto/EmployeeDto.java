package com.example.webHelpDesk.domain.dto;

import com.example.webHelpDesk.domain.entity.Ticket;
import com.example.webHelpDesk.domain.reference.Department;


public class EmployeeDto {
    private Long id;
    private Long employeeNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private Department department;
    private Ticket ticket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String firstName, String middleName, String lastName) {
        this.fullName = firstName +" " + middleName + " " +lastName;
    }

}
