package com.pw.cinema.movie_category;

import com.pw.cinema.exceptions.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieCategoryController {

    @Autowired
    private MovieCategoryService movieCategoryService;

    @GetMapping(path = "/movies/categories")
    public ResponseEntity<Object> getListMovieCategories() {
        return ResponseEntity.ok().body(movieCategoryService.getAllCategories());
    }

    // Just for testing Request Body to use in Movie
    @GetMapping(path = "/movies/category/id")
    public ResponseEntity<Object> getCategory(@RequestBody MovieCategory movieCategory) {
        return ResponseEntity.ok().body(movieCategoryService.getCategory(movieCategory));
    }


    @PostMapping(path = "/movies/categories")
    public ResponseEntity<Object> addMovieCategories(@RequestBody MovieCategory movieCategory) throws AlreadyExistsException {
        return ResponseEntity.ok().body(movieCategoryService.create(movieCategory));
    }

    @PatchMapping(path = "/movies/category")
    public ResponseEntity<Object> updateMovieCategories(@RequestBody MovieCategory movieCategory,
                                                        @RequestParam Long id) {
        return ResponseEntity.ok().body(movieCategoryService.updateCategory(movieCategory, id));
    }


    @DeleteMapping(path = "/movies/category")
    public ResponseEntity<Object> deleteMovieCategory(@RequestParam Long id) {
        return ResponseEntity.ok().body(movieCategoryService.deleteCategory(id));
    }

}
