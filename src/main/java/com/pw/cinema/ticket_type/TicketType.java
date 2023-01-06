package com.pw.cinema.ticket_type;

import com.pw.cinema.seat_type.SeatType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket_type")
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @ManyToOne
    @JoinColumn(name = "seat_type_id")
    SeatType seatType;
    float price;
    String description;
}
