package com.example.webHelpDesk.repository;

import com.example.webHelpDesk.domain.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
