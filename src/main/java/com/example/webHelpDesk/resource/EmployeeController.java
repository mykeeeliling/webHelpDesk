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

    @GetMapping(path = "/list")
    private ResponseEntity<Employee> list(){
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

    @PostMapping(path = "/create")
    private ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.create(employeeDto));
    }

//    @PutMapping(path = "/{employeeNumber}")
//    private ResponseEntity<Employee> update(@PathVariable(name = "employeeNumber") Long employeeNumber,
//                                            @RequestBody Employee employee){
//        return ResponseEntity.ok(employeeService.update(employee));
//    }
}
