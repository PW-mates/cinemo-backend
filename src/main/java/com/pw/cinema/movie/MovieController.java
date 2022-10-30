package com.pw.cinema.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(path="/movies")
    public ResponseEntity<Object> getMovieById(@RequestParam Long id) {
        return ResponseEntity.ok().body(movieService.getMovieById(id));
    }

}
