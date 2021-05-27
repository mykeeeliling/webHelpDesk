package com.example.webHelpDesk.resource;

import com.example.webHelpDesk.domain.dto.EmployeeDto;
import com.example.webHelpDesk.domain.dto.TicketDto;
import com.example.webHelpDesk.domain.entity.Employee;
import com.example.webHelpDesk.domain.entity.Ticket;
import com.example.webHelpDesk.repository.EmployeeRepository;
import com.example.webHelpDesk.repository.TicketRepository;
import com.example.webHelpDesk.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "ticket")
public class TicketController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TicketService ticketService;


    @GetMapping(path = "{ticketNumber}")
    private ResponseEntity<TicketDto> view(@PathVariable("ticketNumber")Long ticketNumber){

        Ticket ticket = ticketService.view(ticketNumber);
        // Entity -> Dto
        TicketDto ticketDto = modelMapper.map(ticket, TicketDto.class);
        return ResponseEntity.ok().body(ticketDto);
    }

    @GetMapping(path = "list")
    private ResponseEntity<TicketDto> list() {
        return new ResponseEntity(ticketService.list()
                .stream().map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<TicketDto> create(@RequestBody TicketDto ticketDto){

        // Convert Dto -> Entity
        Ticket ticketRequest = modelMapper.map(ticketDto, Ticket.class);

        // Save data to DB using create method()
        // passing converted Dto -> entity as parameter
        Ticket ticket = ticketService.create(ticketRequest);

        // Convert back from Entity -> Dto
        // for returning values to the front end
        TicketDto ticketResponse = modelMapper.map(ticketDto, TicketDto.class);

        return new ResponseEntity<TicketDto>(ticketResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{ticketNumber}")
    private ResponseEntity<TicketDto> update(@PathVariable("ticketNumber") Long ticketNumber,
                                             @RequestBody TicketDto ticketDto){

        // Convert Dto -> Entity
        Ticket ticketRequest = modelMapper.map(ticketDto, Ticket.class);

        // Save data to DB using create method()
        // passing converted Dto -> entity as parameter
        Ticket ticket = ticketService.update(ticketNumber, ticketRequest);

        // Convert back from Entity -> Dto
        // for returning values to the front end
        TicketDto ticketResponse = modelMapper.map(ticket, TicketDto.class);

        return new ResponseEntity<TicketDto>(ticketResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{ticketNumber}")
    private ResponseEntity delete(@PathVariable(name = "ticketNumber") Long ticketNumber){
        ticketService.delete(ticketNumber);
        return new ResponseEntity("",HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{ticketNumber}/watcher/{employeeNumber}")
    private ResponseEntity<TicketDto> assignWatcher(@PathVariable(name = "employeeNumber")Long employeeNumber,
                                                    @PathVariable(name = "ticketNumber")Long ticketNumber){
        return new ResponseEntity(ticketService.assignWatcher(employeeNumber, ticketNumber)
                .stream().map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }
}
