package com.example.webHelpDesk.service.impl;

import com.example.webHelpDesk.domain.dto.EmployeeDto;
import com.example.webHelpDesk.domain.entity.Employee;
import com.example.webHelpDesk.repository.EmployeeRepository;
import com.example.webHelpDesk.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee view(Long employeeNumber){
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber);
        if (employee == null){
            throw new IllegalStateException("Employee Number "+ employeeNumber + " does not exist");
        } else {
            return employee;
        }
    }

    @Override
    public List<Employee> list() {
        List<Employee> employeeList = employeeRepository.findAll();
        if (employeeList.isEmpty()){
            throw new IllegalStateException("Employee list is empty");
        }
        return employeeList;
    }

    @Override
        public EmployeeDto create(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findByEmployeeNumber(employeeDto.getEmployeeNumber());

        if (employee != null){
            throw new IllegalStateException("Employee Number "+ employeeDto.getEmployeeNumber()+ " already exist");
        }
         else {
             employee = new Employee();
            employee.setEmployeeNumber(employeeDto.getEmployeeNumber());
            employee.setFirstName(employeeDto.getFirstName());
            employee.setMiddleName(employeeDto.getMiddleName());
            employee.setLastName(employeeDto.getLastName());
            employee.setDepartment(employeeDto.getDepartment());
            employeeRepository.save(employee);
            employeeDto.setId(employee.getId());
        }
        return employeeDto;
    }
//
//    @Override
//    public Employee update(Employee employeeDetails) {
//        boolean employeeNumberExists = employeeRepository.existsByEmployeeNumber(employeeDetails.getEmployeeNumber());
//        if (!employeeNumberExists){
//            throw new IllegalStateException("Employee Number "+ employeeDetails.getEmployeeNumber()+ " does not exist");
//        }
//        Employee employee = employeeRepository.findByEmployeeNumber(employeeDetails.getEmployeeNumber());
//
//        if (employeeDetails.getFirstName() != null){
//            employee.setFirstName(employeeDetails.getFirstName());
//        }
//        if (employeeDetails.getMiddleName() != null){
//            employee.setMiddleName(employeeDetails.getMiddleName());
//        }
//        if (employeeDetails.getLastName() != null){
//            employee.setLastName(employeeDetails.getLastName());
//        }
//        if (employeeDetails.getDepartment() != null) {
//            employee.setDepartment(employeeDetails.getDepartment());
//        }
//        return employeeRepository.save(employee);
//    }


}
