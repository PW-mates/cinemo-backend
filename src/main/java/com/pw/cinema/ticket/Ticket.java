package com.pw.cinema.ticket;

import com.pw.cinema.payment.Payment;
import com.pw.cinema.screening.Screening;
import com.pw.cinema.seat.Seat;
import com.pw.cinema.user.User;
import com.sun.istack.NotNull;
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
    @NotNull
    String code;
    @ManyToOne
    @JoinColumn(name = "screening_id")
    @NotNull
    Screening screening;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Seat> seats;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    @NotNull
    User seller;
    @NotNull
    float totalPrice;
    @NotNull
    Long createAt;
    @NotNull
    String paymentMethod;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    @NotNull
    Payment payment;
    @NotNull
    String status;
}
