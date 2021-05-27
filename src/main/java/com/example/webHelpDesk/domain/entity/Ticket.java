package com.example.webHelpDesk.domain.entity;

import javax.persistence.*;
import com.example.webHelpDesk.domain.reference.Severity;
import com.example.webHelpDesk.domain.reference.Status;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Ticket")
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "ticket_number",
            nullable = false
    )
    private Long ticketNumber;

    @Column(nullable = false)
    private String title;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;


    @Enumerated
    @Column(nullable = false)
    private Severity severity;

    @Enumerated
    @Column(nullable = false)
    private Status status;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "ticket")
    private Employee assignee;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "watchers",
            joinColumns = {@JoinColumn(name = "ticketNumber")},
            inverseJoinColumns = {@JoinColumn(name = "employeeNumber")})
    private List<Employee> watchers = new ArrayList<>();

    public Ticket() {
    }

    public Long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Employee getAssignee() {
        return assignee;
    }

    public void setAssignee(Employee assignee) {
        this.assignee = assignee;
    }

    public List<Employee> getWatchers() {
        return watchers;
    }

    public void setWatchers(List<Employee> watchers) {
        this.watchers = watchers;
    }
}
