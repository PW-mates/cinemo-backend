package com.pw.cinema.ticket_type;

import com.pw.cinema.seat_type.SeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TicketTypeService {
    @Autowired
    TicketTypeRepository ticketTypeRepository;
    @Autowired
    SeatTypeRepository seatTypeRepository;

    public Object createTicketType(TicketType ticketType) {
        ticketType.setSeatType(
                seatTypeRepository.findById(ticketType.getSeatType().getId())
                        .orElseThrow(()
                                -> new IllegalStateException("SeatType doesn't exist")));
        TicketType savedTicketType = ticketTypeRepository.save(ticketType);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", savedTicketType);
        resp.put("message", "Successful create room");
        return resp;
    }
}
