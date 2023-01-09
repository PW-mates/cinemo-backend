package com.pw.cinema.ticket;


import com.pw.cinema.payment.PaymentRepository;
import com.pw.cinema.room.RoomDto;
import com.pw.cinema.screening.ScreeningRepository;
import com.pw.cinema.seat.Seat;
import com.pw.cinema.seat.SeatRepository;
import com.pw.cinema.user.UserRepository;
import com.pw.cinema.utils.Utils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.pw.cinema.utils.Utils.response;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ScreeningRepository screeningRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    ModelMapper modelMapper;

    public Object create(Ticket ticket) {
        // should be without payment is null
        ticket.setSeller(userRepository.findById(ticket.getSeller().getId()).
                orElseThrow(() -> new IllegalStateException("Seller does not exist")));
        ticket.setScreening(screeningRepository.findById(ticket.getScreening().getId())
                .orElseThrow(() -> new IllegalStateException("Screening does not exist")));
        int codeLength = 10;
        String newCode = Utils.getAlphaNumericString(codeLength);
        ticket.setCode(newCode);
        Set<Long> seatIds = ticket.getSeats().stream().map(Seat::getId).collect(Collectors.toSet());
        if (!seatRepository.existsAllByIdIn(seatIds)) {
            throw new IllegalStateException("Not found seats");
        }
        ticket.setSeats(seatRepository.findAllByIdIn(seatIds));
        float totalPrice = 0;
        for (Seat seat : ticket.getSeats()) {
            totalPrice += seat.getType().getPrice();
        }
        ticket.setTotalPrice(totalPrice);
        Ticket savedTicket = ticketRepository.save(ticket);
        return response(convertEntityToDto(savedTicket), "Successfully created a ticket");
    }

    public TicketDto convertEntityToDto(Ticket ticket) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(ticket, TicketDto.class);
    }

    public Object getTickets() {
        List<TicketDto> allTickets =
                ticketRepository.findAll().stream().map(this::convertEntityToDto).toList();
        return response(allTickets, "Successfully found ticket data");
    }

    public Object getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not found ticket"));
        return response(convertEntityToDto(ticket), "Successfully found ticket");
    }

    public Object deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new NoSuchElementException("Not found ticket with this id");
        }
        ticketRepository.deleteById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully deleted ticket");
        response.put("success", true);
        return response;
    }

    public Object updateTicket(Long id, Ticket ticket) {
        if (!ticketRepository.existsById(id))
            throw new NoSuchElementException("Not found ticket with this id");
        ticket.setSeller(userRepository.findById(ticket.getSeller().getId()).
                orElseThrow(() -> new IllegalStateException("Seller does not exist")));
        ticket.setScreening(screeningRepository.findById(ticket.getScreening().getId())
                .orElseThrow(() -> new IllegalStateException("Screening does not exist")));
        ticket.setPayment(paymentRepository.findById(ticket.getPayment().getId())
                .orElseThrow(() -> new IllegalStateException("Payment does not exist")));
        Set<Long> seatIds = ticket.getSeats().stream().map(Seat::getId).collect(Collectors.toSet());
        if (!seatRepository.existsAllByIdIn(seatIds)) {
            throw new IllegalStateException("Not found seats");
        }
        ticket.setSeats(seatRepository.findAllByIdIn(seatIds));
        float totalPrice = 0;
        for (Seat seat : ticket.getSeats()) {
            totalPrice += seat.getType().getPrice();
        }
        ticket.setTotalPrice(totalPrice);
        Ticket newTicket = ticketRepository.save(ticket);
        return response(convertEntityToDto(newTicket), "Successfully updated ticket");

    }

}
