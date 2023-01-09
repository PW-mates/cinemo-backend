package com.pw.cinema.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping(path = "tickets")
    public ResponseEntity<Object> createTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok().body(ticketService.create(ticket));
    }
}
