package com.example.webHelpDesk.service.impl;

import com.example.webHelpDesk.domain.entity.Employee;
import com.example.webHelpDesk.domain.entity.Ticket;
import com.example.webHelpDesk.repository.EmployeeRepository;
import com.example.webHelpDesk.repository.TicketRepository;
import com.example.webHelpDesk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final TicketRepository ticketRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TicketRepository ticketRepository) {
        this.employeeRepository = employeeRepository;
        this.ticketRepository = ticketRepository;
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
        public Employee create(Employee employeeRequest) {
        Employee employee = employeeRepository.findByEmployeeNumber(employeeRequest.getEmployeeNumber());
        if (employee != null){
            throw new IllegalStateException("Employee Number " + employee + " already exist.");
        }
        return employeeRepository.save(employeeRequest);
    }

    @Override
    public Employee update(Long employeeNumber, Employee employeeDetails) {
        boolean employeeNumberExists = employeeRepository.existsByEmployeeNumber(employeeNumber);
        if (!employeeNumberExists){
            throw new IllegalStateException("Employee Number "+ employeeDetails.getEmployeeNumber()+ " does not exist");
        }
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber);

        if (employeeDetails.getFirstName() != null){
            employee.setFirstName(employeeDetails.getFirstName());
        }
        if (employeeDetails.getMiddleName() != null){
            employee.setMiddleName(employeeDetails.getMiddleName());
        }
        if (employeeDetails.getLastName() != null){
            employee.setLastName(employeeDetails.getLastName());
        }
        if (employeeDetails.getDepartment() != null) {
            employee.setDepartment(employeeDetails.getDepartment());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long employeeNumber) {
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber);
        if (employee == null){
            throw new IllegalStateException("Employee Number " + employeeNumber + " does not exist");
        }
        employeeRepository.delete(employee);
    }

    @Override
    public void assignTicket(Long employeeNumber, Long ticketNumber) {
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber);
        if (employee == null){
            throw new IllegalStateException("Employee Number does not exist");
        }
        Ticket ticket = ticketRepository.findById(ticketNumber).orElseThrow(()
                -> new IllegalStateException("Ticket number does not exists"));
        ticket.setAssignee(employee);

        employee.setTicket(ticket);
        employeeRepository.save(employee);
    }

}
