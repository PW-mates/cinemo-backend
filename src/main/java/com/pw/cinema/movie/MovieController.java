package com.pw.cinema.movie;

import com.pw.cinema.exceptions.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "/movies")
    public ResponseEntity<Object> getAllMovies() {
        return ResponseEntity.ok().body(movieService.getMovieList());
    }

    @PostMapping(path = "/movies")
    public ResponseEntity<Object> createMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(movieService.create(movie));
    }

    @PatchMapping(path = "/movies/{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
        return ResponseEntity.ok().body(movieService.updateMovie(movie, id));
    }

    @DeleteMapping(path = "/movies/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(movieService.deleteMovie(id));
    }

    @GetMapping(path = "/movies/{id}")
    public ResponseEntity<Object> getMovie(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(movieService.getMovieById(id));
    }

    @PostMapping(path = "/movies/{id}/poster")
    //  base64 encoded image
    public ResponseEntity<Object> uploadPoster(@PathVariable("id") Long id, @RequestBody String poster) {
        return ResponseEntity.ok().body(movieService.uploadPoster(id, poster));
    }
}
