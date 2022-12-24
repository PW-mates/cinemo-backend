package com.pw.cinema.theater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TheaterController {
    @Autowired
    TheaterService theaterService;

    @PostMapping(path= "theaters")
    public ResponseEntity<Object> createTheater(@RequestBody Theater theater) {
        return ResponseEntity.ok().body(theaterService.createTheater(theater));
    }

    @GetMapping(path= "theaters")
    public ResponseEntity<Object> getTheaters() {
        return ResponseEntity.ok().body(theaterService.getTheaters());
    }

    @PatchMapping(path= "theaters/{id}")
    public ResponseEntity<Object> updateTheater(@RequestBody Theater theater, @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(theaterService.updateTheater(id, theater));
    }
}
