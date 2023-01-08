package com.pw.cinema.ticket_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TicketTypeController {
    @Autowired
    TicketTypeService ticketTypeService;

    @PostMapping(path = "ticket-types")
    public ResponseEntity<Object> createTicketType(@RequestBody TicketType ticketType) {
        return ResponseEntity.ok().body(ticketTypeService.createTicketType(ticketType));
    }
}
