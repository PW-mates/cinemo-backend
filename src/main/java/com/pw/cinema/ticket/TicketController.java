package com.pw.cinema.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping(path = "tickets")
    public ResponseEntity<Object> createTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok().body(ticketService.create(ticket));
    }

    @GetMapping(path = "tickets")
    public ResponseEntity<Object> getTickets() {
        return ResponseEntity.ok().body(ticketService.getTickets());
    }

    @GetMapping(path = "tickets/{id}")
    public ResponseEntity<Object> getTicket(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(ticketService.getTicket(id));
    }

    @DeleteMapping(path = "tickets/{id}")
    public ResponseEntity<Object> deleteTicket(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(ticketService.deleteTicket(id));
    }

    @PatchMapping(path = "tickets/{id}")
    public ResponseEntity<Object> updateTicket(@PathVariable("id") Long id, @RequestBody Ticket ticket) {
        return ResponseEntity.ok().body(ticketService.updateTicket(id, ticket));
    }
}
