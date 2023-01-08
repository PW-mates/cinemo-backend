package com.pw.cinema.ticket_type;

import com.pw.cinema.room.Room;
import com.pw.cinema.seat_type.SeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
        resp.put("message", "Successful create ticket type");
        return resp;
    }

    public Object getTicketTypes() {
        List<TicketType> ticketTypeList = ticketTypeRepository.findAll();
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", ticketTypeList);
        resp.put("message", "Successful get ticket types");
        return resp;
    }

    public Object getTicketType(Long id) {
        TicketType savedTicketType = ticketTypeRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Ticket type with id not found")
        );
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", savedTicketType);
        resp.put("message", "Successful get ticket type");
        return resp;
    }
}
