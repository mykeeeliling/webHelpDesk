package com.example.webHelpDesk.service;

import com.example.webHelpDesk.domain.dto.EmployeeDto;
import com.example.webHelpDesk.domain.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee view(Long employeeNumber);
    List<Employee> list();
    Employee create(Employee Employee);
    Employee update(Long employeeNumber, Employee employee);
    void delete(Long employeeNumber);
    void assignTicket(Long employeeNumber, Long ticketNumber);
}
