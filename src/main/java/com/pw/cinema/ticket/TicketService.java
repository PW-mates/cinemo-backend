package com.pw.cinema.ticket;

import com.pw.cinema.screening.ScreeningRepository;
import com.pw.cinema.ticket_type.TicketTypeRepository;
import com.pw.cinema.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ScreeningRepository screeningRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TicketTypeRepository ticketTypeRepository;
    public Object create(Ticket ticket) {
        ticket.setTicketType(ticketTypeRepository
                .findById(ticket.getTicketType().getId())
                .orElseThrow(()
                        -> new IllegalStateException("Ticket type doesn't exist")));
        // Do the same for seller
        // Do the same for payment
        // Do the same for screening
        // Maybe Dto is needed in this case!

        Ticket savedTicket = ticketRepository.save(ticket);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", savedTicket);
        resp.put("message", "Successful create room");
        return resp;
    }
}
