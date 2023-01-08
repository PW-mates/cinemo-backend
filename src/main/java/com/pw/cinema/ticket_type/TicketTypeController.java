package com.pw.cinema.ticket_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketTypeController {
    @Autowired
    TicketTypeService ticketTypeService;

    @PostMapping(path = "ticket-types")
    public ResponseEntity<Object> createTicketType(@RequestBody TicketType ticketType) {
        return ResponseEntity.ok().body(ticketTypeService.createTicketType(ticketType));
    }

    @GetMapping(path = "ticket-types")
    public ResponseEntity<Object> getTicketTypes() {
        return ResponseEntity.ok().body(ticketTypeService.getTicketTypes());
    }

    @GetMapping(path = "ticket-types/{id}")
    public ResponseEntity<Object> getTicketType(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(ticketTypeService.getTicketType(id));
    }

    @PatchMapping(path = "ticket-types/{id}")
    public ResponseEntity<Object> updateTicketType(@PathVariable("id") Long id,
                                                @RequestBody TicketType ticketType) {
        return ResponseEntity.ok().body(ticketTypeService.updateTicketType(id, ticketType));
    }

}
