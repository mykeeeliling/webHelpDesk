package com.example.webHelpDesk.domain.dto;

import com.example.webHelpDesk.domain.entity.Employee;
import com.example.webHelpDesk.domain.reference.Severity;
import com.example.webHelpDesk.domain.reference.Status;

import java.util.List;

public class TicketDto {
    private Long ticketNumber;
    private String title;
    private String description;
    private Severity severity;
    private Status status;
    private Employee assignee;
    private List<Employee> watchers;

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
