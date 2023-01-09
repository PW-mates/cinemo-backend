package com.pw.cinema.ticket;

import com.pw.cinema.payment.Payment;
import com.pw.cinema.screening.Screening;
import com.pw.cinema.seat.SeatDto;
import com.pw.cinema.user.UserBasicDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketDto {
    @Id
    Long id;
    String code;
    Screening screening;
    Set<SeatDto> seats;
    UserBasicDto seller;
    float totalPrice;
    Long createAt;
    String paymentMethod;
    Payment payment;
    String status;
}
