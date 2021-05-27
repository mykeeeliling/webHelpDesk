package com.example.webHelpDesk.service;

import com.example.webHelpDesk.domain.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket view(Long ticketNumber);
    List<Ticket> list();
    Ticket update(Long ticketNumber, Ticket ticket);
    Ticket create(Ticket ticket);
    void delete(Long ticketNumber);
    List<Ticket> assignWatcher(Long employeeNumber, Long ticketNumber);
}
