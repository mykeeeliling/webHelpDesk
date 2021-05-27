package com.example.webHelpDesk.service.impl;

import com.example.webHelpDesk.domain.dto.TicketDto;
import com.example.webHelpDesk.domain.entity.Employee;
import com.example.webHelpDesk.domain.entity.Ticket;
import com.example.webHelpDesk.repository.EmployeeRepository;
import com.example.webHelpDesk.repository.TicketRepository;
import com.example.webHelpDesk.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private final TicketRepository ticketRepository;

    @Autowired
    private final EmployeeRepository employeeRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, EmployeeRepository employeeRepository) {
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Ticket view(Long ticketNumber) {
        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber);
        if (ticket == null) {
            throw new IllegalStateException("Ticket number " + ticketNumber + " does not exist");
        }
        return ticket;
    }

    @Override
    public List<Ticket> list() {
        List<Ticket> ticketList = ticketRepository.findAll();
        if (ticketList.isEmpty()){
            throw new IllegalStateException("Ticket list is empty");
        }
        return ticketList;
    }


    @Override
    public Ticket create(Ticket ticketRequest) {
        Ticket ticket = ticketRepository.findByTicketNumber(ticketRequest.getTicketNumber());
        if (ticket != null){
            throw new IllegalStateException("Ticket Number " + ticket + " already exist.");
        }
        return ticketRepository.save(ticketRequest);
    }

    @Override
    public Ticket update(Long ticketNumber, Ticket ticketRequest) {
        boolean ticketNumberExists = ticketRepository.existsByTicketNumber(ticketNumber);
        if (!ticketNumberExists){
            throw new IllegalStateException("Ticket Number "+ ticketRequest.getTicketNumber()+ " does not exist");
        }
        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber);

        if (ticketRequest.getTitle() != null){
            ticket.setTitle(ticketRequest.getTitle());
        }
        if (ticketRequest.getDescription() != null){
            ticket.setDescription(ticketRequest.getDescription());
        }
        if (ticketRequest.getSeverity() != null){
            ticket.setSeverity(ticketRequest.getSeverity());
        }
        if (ticketRequest.getStatus() != null) {
            ticket.setStatus(ticketRequest.getStatus());
        }
        if (ticketRequest.getAssignee() != null) {
            ticket.setAssignee(ticketRequest.getAssignee());
        }
        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(Long ticketNumber) {
        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber);
        if (ticket == null){
            throw new IllegalStateException("Ticket Number " + ticketNumber + " does not exist");
        }
        ticketRepository.delete(ticket);
    }

    @Override
    public List<Ticket> assignWatcher(Long employeeNumber, Long ticketNumber) {
        Ticket ticketAssign = ticketRepository.findByTicketNumber(ticketNumber);
        Employee watcher = employeeRepository.findByEmployeeNumber(employeeNumber);
        if (ticketAssign == null){
            throw new IllegalStateException("Ticket Id: " + ticketNumber + " doesn't exist");
        }
        if (watcher == null){
            throw new IllegalStateException("Employee Number: " + ticketNumber + " doesn't exist");
        }
        ticketAssign.getWatchers().add(watcher);
        ticketRepository.save(ticketAssign);
        return null;
    }
}
