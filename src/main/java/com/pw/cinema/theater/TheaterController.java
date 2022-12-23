package com.pw.cinema.theater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TheaterController {
    @Autowired
    TheaterService theaterService;

    @PostMapping(path= "theaters")
    public ResponseEntity<Object> createTheater(@RequestBody Theater theater) {
        return ResponseEntity.ok().body(theaterService.createTheater(theater));
    }
}
