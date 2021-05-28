package com.example.webHelpDesk.resource;

import com.example.webHelpDesk.domain.dto.EmployeeDto;
import com.example.webHelpDesk.domain.entity.Employee;
import com.example.webHelpDesk.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "employee")
public class EmployeeController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    private ResponseEntity<EmployeeDto> list(){
        return new ResponseEntity(employeeService.list()
                .stream().map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList())
                ,HttpStatus.OK);
    }

    @GetMapping(path = "/{employeeNumber}")
    private ResponseEntity<EmployeeDto> view(@PathVariable("employeeNumber")Long employeeNumber){
        Employee employee = employeeService.view(employeeNumber);

        // Convert entity to DTO
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return ResponseEntity.ok().body(employeeDto);
    }

    @PostMapping
    private ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto){

        // Convert Dto -> Entity
        Employee employeeRequest = modelMapper.map(employeeDto, Employee.class);

        // Save data to DB using create method()
        // passing converted Dto -> entity as parameter
        Employee employee = employeeService.create(employeeRequest);

        // Convert back from Entity -> Dto
        // for returning values to the front end
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);

        return new ResponseEntity<EmployeeDto>(employeeResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeNumber}")
    private ResponseEntity<EmployeeDto> update(@PathVariable(name = "employeeNumber") Long employeeNumber,
                                                @RequestBody EmployeeDto employeeDto){
        // Convert Dto -> Entity
        Employee employeeRequest = modelMapper.map(employeeDto, Employee.class);

        // Save data to DB using create method()
        // passing converted Dto -> entity as parameter
        Employee employee = employeeService.update(employeeNumber, employeeRequest);

        // Convert back from Entity -> Dto
        // for returning values to the front end
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);

        return ResponseEntity.ok().body(employeeResponse);
    }

    @DeleteMapping(path = "/{employeeNumber}")
    private ResponseEntity delete(@PathVariable(name = "employeeNumber") Long employeeNumber){
        employeeService.delete(employeeNumber);
        return new ResponseEntity("",HttpStatus.NO_CONTENT);
    }
    
    @PutMapping(path = "/{employeeNumber}/ticket/{ticketNumber}")
    private void assignTicket(@PathVariable(name = "employeeNumber") Long employeeNumber,
                              @PathVariable(name = "ticketNumber")Long ticketNumber){
        employeeService.assignTicket(employeeNumber, ticketNumber);
    }
}
