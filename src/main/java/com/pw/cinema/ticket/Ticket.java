package com.pw.cinema.ticket;

import com.pw.cinema.payment.Payment;
import com.pw.cinema.screening.Screening;
import com.pw.cinema.seat.Seat;
import com.pw.cinema.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    @ManyToOne
    @JoinColumn(name = "screening_id")
    Screening screening;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Seat> seats;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    User seller;
    float totalPrice;
    Long createAt;
    String paymentMethod;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    Payment payment;
    String status;
}
