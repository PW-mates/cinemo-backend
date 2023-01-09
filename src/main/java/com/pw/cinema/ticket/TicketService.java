package com.pw.cinema.ticket;

import com.pw.cinema.movie_category.MovieCategory;
import com.pw.cinema.payment.PaymentRepository;
import com.pw.cinema.screening.ScreeningRepository;
import com.pw.cinema.seat.Seat;
import com.pw.cinema.seat.SeatRepository;
import com.pw.cinema.ticket_type.TicketTypeRepository;
import com.pw.cinema.user.UserRepository;
import static com.pw.cinema.utils.Utils.response;
import com.pw.cinema.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Object create(Ticket ticket) {
        ticket.setSeller(userRepository.findById(ticket.getSeller().getId()).
                orElseThrow(() -> new IllegalStateException("Seller does not exist")));
        ticket.setScreening(screeningRepository.findById(ticket.getScreening().getId())
                .orElseThrow(()->new IllegalStateException("Screening does not exist")));
        int codeLength = 10;
        String newCode = Utils.getAlphaNumericString(codeLength);
        ticket.setCode(newCode);
        Set<Long> seatIds = ticket.getSeats().stream().map(Seat::getId).collect(Collectors.toSet());
        if (!seatRepository.existsAllByIdIn(seatIds)){
            throw new IllegalStateException("Not found seats");
        }
        ticket.setSeats(seatRepository.findAllById(seatIds));
        float totalPrice = 0;
        for (Seat seat : ticket.getSeats()) {
            totalPrice += seat.getType().getPrice();
        }
        ticket.setTotalPrice(totalPrice);
        // Maybe Dto is needed in this case! later
        Ticket savedTicket = ticketRepository.save(ticket);
        return response(savedTicket, "Successfully created a ticket");
    }
}
