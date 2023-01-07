package com.pw.cinema.ticket_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketTypeService {
    @Autowired
    TicketTypeRepository ticketTypeRepository;
}
