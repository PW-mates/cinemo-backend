package com.pw.cinema.moviecategory;


import com.pw.cinema.exceptions.HasMoviesException;
import com.pw.cinema.movie_category.MovieCategory;
import com.pw.cinema.movie_category.MovieCategoryRepository;
import com.pw.cinema.movie_category.MovieCategoryService;
import com.pw.cinema.utils.Utils;
import liquibase.pro.packaged.M;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MovieCategoryServiceTest {
    @Autowired
    MovieCategoryRepository movieCategoryRepository;
    @Autowired
    MovieCategoryService movieCategoryService;

    @Test
    @Order(1)
    void getMovieCategory() {
        Object resp = movieCategoryService.getCategory(2L);
        MovieCategory movieCategory = new MovieCategory(2L, "adventure", "adventure-category");
        Object expected = Utils.response(movieCategory, "Successfully found category");
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @Order(2)
    void updateMovieCategory() {
        MovieCategory movieCategory = new MovieCategory(2L, "adventure", "advent-category");
        Object resp = movieCategoryService.updateCategory(movieCategory, 2L);
        Object expected = Utils.response(movieCategory, "Successfully updated category");
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @Order(3)
    void getMovieCategoryNotFound() {
        Exception exception = assertThrows(Exception.class, () -> movieCategoryService.getCategory(20L));
        Assertions.assertEquals("Not found category with this id", exception.getMessage());
    }

    @Test
    @Order(4)
    void deleteUsedCategory() {
        // when movies are added
//        Exception exception = assertThrows(Exception.class, () -> movieCategoryService.deleteCategory(11L));
//        Assertions.assertEquals("This category can not be deleted, because it includes movies", exception.getMessage());
    }

    @Test
    @Order(5)
    void deleteMovieCategory() throws HasMoviesException {
        Object resp = movieCategoryService.deleteCategory( 5L);
        Map<String, Object> expected = new HashMap<>();
        expected.put("message", "Successfully deleted this category");
        expected.put("success", true);
        Optional<MovieCategory> category = movieCategoryRepository.findById(2L);
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @Order(6)
    void updateCategoryNotFound() {
        // when movies are added
        MovieCategory movieCategory = new MovieCategory(25L, "christmas", "christmas-category");
        Exception exception = assertThrows(Exception.class, () -> movieCategoryService.updateCategory(movieCategory,
                25L));
        Assertions.assertEquals("Not found category with this id", exception.getMessage());
    }

    @Test
    @Order(7)
    void deleteMovieCategoryNotFound() {
        Exception exception = assertThrows(Exception.class, () -> movieCategoryService.deleteCategory(20L));
        Assertions.assertEquals("Not found category with this id", exception.getMessage());
    }


}
