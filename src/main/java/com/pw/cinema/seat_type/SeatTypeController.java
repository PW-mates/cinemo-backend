package com.pw.cinema.seat_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeatTypeController {
    @Autowired
    private SeatTypeService seatTypeService;
    @GetMapping(path = "seat-types")
    public ResponseEntity<Object> getSeatTypes() {
        return ResponseEntity.ok().body(seatTypeService.getSeatTypes());
    }
}
