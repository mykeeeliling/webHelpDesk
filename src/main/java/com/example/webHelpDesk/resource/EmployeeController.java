package com.example.webHelpDesk.resource;

import com.example.webHelpDesk.domain.dto.EmployeeDto;
import com.example.webHelpDesk.domain.entity.Employee;
import com.example.webHelpDesk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/list")
    private ResponseEntity<Employee> list(){
        return new ResponseEntity(employeeService.list(),HttpStatus.OK);
    }

    @GetMapping(path = "/{employeeNumber}")
    private ResponseEntity<Employee> view(@PathVariable("employeeNumber")Long employeeNumber){
        return new ResponseEntity(employeeService.view(employeeNumber),HttpStatus.OK);
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
