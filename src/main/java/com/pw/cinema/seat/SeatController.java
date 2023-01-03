package com.pw.cinema.seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SeatController {
    @Autowired
    SeatService seatService;
    @GetMapping(path = "seats")
    public ResponseEntity<Object> getSeats() {
        return ResponseEntity.ok().body(seatService.getSeats());
    }
    @PostMapping(path = "seats")
    public ResponseEntity<Object> createSeat(@PathVariable Seat seat) {
        return ResponseEntity.ok().body(seatService.createSeat(seat));
    }
}
