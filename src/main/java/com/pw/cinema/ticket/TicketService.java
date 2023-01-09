package com.pw.cinema.ticket;

import com.pw.cinema.movie_category.MovieCategory;
import com.pw.cinema.payment.PaymentRepository;
import com.pw.cinema.room.Room;
import com.pw.cinema.room.RoomDto;
import com.pw.cinema.screening.ScreeningRepository;
import com.pw.cinema.seat.Seat;
import com.pw.cinema.seat.SeatRepository;
import com.pw.cinema.ticket_type.TicketTypeRepository;
import com.pw.cinema.user.UserRepository;
import static com.pw.cinema.utils.Utils.response;
import com.pw.cinema.utils.Utils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
    @Autowired
    ModelMapper modelMapper;

    public Object create(Ticket ticket) {
        // should be without payment is null
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
        ticket.setSeats(seatRepository.findAllByIdIn(seatIds));
        float totalPrice = 0;
//        for (Seat seat : ticket.getSeats()) {
//            totalPrice += seat.getType().getPrice();
//        }
        ticket.setTotalPrice(totalPrice);
        Ticket savedTicket = ticketRepository.save(ticket);
        return response(convertEntityToDto(savedTicket), "Successfully created a ticket");
    }

    public TicketDto convertEntityToDto(Ticket ticket) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(ticket, TicketDto.class);
    }
}
