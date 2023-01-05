package com.pw.cinema.seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SeatController {
    @Autowired
    SeatService seatService;
    @GetMapping(path = "seats")
    public ResponseEntity<Object> getSeats() {
        return ResponseEntity.ok().body(seatService.getSeats());
    }
    @PostMapping(path = "seats")
    public ResponseEntity<Object> createSeat(@RequestBody Seat seat) {
        return ResponseEntity.ok().body(seatService.createSeat(seat));
    }

    @PatchMapping(path = "seats/{id}")
    public ResponseEntity<Object> updateSeat(@PathVariable("id") Long id, @RequestBody Seat seat) {
        return ResponseEntity.ok().body(seatService.updateSeat(id, seat));
    }
}
