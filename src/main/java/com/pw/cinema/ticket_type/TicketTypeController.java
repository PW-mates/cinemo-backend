package com.pw.cinema.ticket_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketTypeController {
    @Autowired
    TicketTypeService ticketTypeService;
}
