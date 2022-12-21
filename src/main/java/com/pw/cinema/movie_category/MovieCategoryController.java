package com.pw.cinema.movie_category;

import com.pw.cinema.exceptions.AlreadyExistsException;
import com.pw.cinema.exceptions.HasMoviesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieCategoryController {

    private final MovieCategoryService movieCategoryService;

    public MovieCategoryController(MovieCategoryService movieCategoryService) {
        this.movieCategoryService = movieCategoryService;
    }


    @GetMapping(path = "/movie-categories")
    public ResponseEntity<Object> getListMovieCategories() {
        return ResponseEntity.ok().body(movieCategoryService.getAllCategories());
    }

    @GetMapping(path = "/movie-categories/{id}")
    public ResponseEntity<Object> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieCategoryService.getCategory(id));
    }

    @PostMapping(path = "/movie-categories")
    public ResponseEntity<Object> createMovieCategories(@RequestBody MovieCategory movieCategory) throws AlreadyExistsException {
        return ResponseEntity.ok().body(movieCategoryService.create(movieCategory));
    }

    @PatchMapping(path = "/movie-categories/{id}")
    public ResponseEntity<Object> updateMovieCategories(@RequestBody MovieCategory movieCategory,
                                                        @PathVariable Long id) {
        return ResponseEntity.ok().body(movieCategoryService.updateCategory(movieCategory, id));
    }

    @DeleteMapping(path = "/movie-categories/{id}")
    public ResponseEntity<Object> deleteMovieCategory(@PathVariable Long id) throws HasMoviesException {
        return ResponseEntity.ok().body(movieCategoryService.deleteCategory(id));
    }

}
