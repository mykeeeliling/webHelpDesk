package com.example.webHelpDesk.service;

import com.example.webHelpDesk.domain.dto.EmployeeDto;
import com.example.webHelpDesk.domain.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> view(Long employeeNumber);
    List<EmployeeDto> list();
    EmployeeDto create(EmployeeDto employeeDto);
//    EmployeeDto update(Long employeeNumber, EmployeeDto employee);
}
