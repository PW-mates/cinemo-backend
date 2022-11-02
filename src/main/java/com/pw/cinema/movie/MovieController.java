package com.pw.cinema.movie;

import com.pw.cinema.exceptions.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(path = "/movies")
    public ResponseEntity<Object> getAllMovies() {
        return ResponseEntity.ok().body(movieService.getMovieList());
    }

    @PostMapping(path = "/movie")
    public ResponseEntity<Object> createMovie(@RequestBody Movie movie) throws AlreadyExistsException {
        return ResponseEntity.ok().body(movieService.create(movie));
    }

    @PatchMapping(path = "/movies/:{id}")
    ////// ID is param
    public ResponseEntity<Object> updateMovie(@RequestBody Movie movie, @PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.updateMovie(movie, id));
    }

    @DeleteMapping(path = "/movies/:{id}")
    ////// ID is param
    public ResponseEntity<Object> deleteMovie(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.deleteMovie(id));
    }

    @GetMapping(path = "/movies/:{id}")
    public ResponseEntity<Object> getMovie(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.getMovieById(id));
    }

    @PostMapping(path="/movies/:{id}/poster")
    //  base64 encoded image
    public ResponseEntity<Object> uploadPoster(@RequestBody String poster, @PathVariable Long id) {
        return  ResponseEntity.ok().body(movieService.uploadPoster(poster, id));
    }

//    @PutMapping(path = "/{movieId}/categories/{categoryId}")
//    public ResponseEntity<Object> addCategoryToMovie(@PathVariable Long movieId, Long categoryId) {
//        return ResponseEntity.ok().body(movieService.addCategoryToMovie(movieId, categoryId));
//    }
}
