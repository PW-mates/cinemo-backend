package com.pw.cinema.movie;

import com.pw.cinema.exceptions.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(path = "/movie")
    public ResponseEntity<Object> getMovieDetails(@RequestParam Long id) {
        return ResponseEntity.ok().body(movieService.getMovieById(id));
    }

    @GetMapping(path = "/movies")
    public ResponseEntity<Object> getAllMovies() {
        return ResponseEntity.ok().body(movieService.getMovieList());
    }

    @PostMapping(path = "/movie")
    public ResponseEntity<Object> addMovie(@RequestBody Movie movie) throws AlreadyExistsException {
        return ResponseEntity.ok().body(movieService.create(movie));
    }

    @PatchMapping(path = "/movie")
    public ResponseEntity<Object> updateMovie(@RequestBody Movie movie, @RequestParam Long id) {
        return ResponseEntity.ok().body(movieService.updateMovie(movie, id));
    }

    @DeleteMapping(path = "/movie")
    public ResponseEntity<Object> deleteMovie(@RequestParam Long id) {
        return ResponseEntity.ok().body(movieService.deleteMovie(id));
    }
    @PutMapping(path = "/{movieId}/categories/{categoryId}")
    public ResponseEntity<Object> addCategoryToMovie(@PathVariable Long movieId, Long categoryId) {
        return ResponseEntity.ok().body(movieService.addCategoryToMovie(movieId, categoryId));
    }
}
