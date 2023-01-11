package com.pw.cinema.movie;

import com.pw.cinema.movie_category.MovieCategory;
import com.pw.cinema.movie_category.MovieCategoryRepository;
import com.pw.cinema.room.Room;
import com.pw.cinema.theater.Theater;
import com.pw.cinema.theater.TheaterDtoLight;
import com.pw.cinema.utils.Utils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.pw.cinema.utils.Utils.*;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieServiceTest {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieService movieService;
    @Autowired
    MovieCategoryRepository movieCategoryRepository;

    @Test
    @Order(1)
    void getMovie() {
        // it works when data is added
        Object resp = movieService.getMovieById(3L);
        Set<Long> ids = new HashSet<>();
        ids.add(12L);
        Set<MovieCategory> categories = movieCategoryRepository.findAllByIdIn(ids);
        Movie movie  = new Movie(3L, "Menu", "A young couple travels to a remote island to eat at an exclusive " +
                "restaurant where the chef has prepared a lavish menu, with some shocking surprises.", "Mark Mylod",
                "null", 1666125417L, 107L, 7L, "https://pelnasala.pl/wp-content/uploads/2022/11/Menu_poster.jpg",
                "https://www.imdb.com/video/vi3154887193/?ref_=tt_vi_i_2", 1665909417L, 1671568617L, categories);
        Object expected = Utils.response(movie, "Successfully found movie");
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @Order(2)
    void getMovies() {
        // now liquibase is not creating movie, so movies are empty
        Object resp =  movieService.getMovieList();
        List<Movie> movies= new ArrayList<>();
        Object expected = Utils.response(movies, "Successfully found movies");
        System.out.println(resp.toString());
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @Order(3)
    void deleteMovies() {
        Object resp =  movieService.deleteMovie(8L);
        Map<String, Object> expected = new HashMap<>();
        expected.put("message", "Successfully deleted movie");
        expected.put("success", true);
        Optional<Movie> movie = movieRepository.findById(2L);
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
        assertFalse(movie.isPresent());
    }

    @Test
    @Order(4)
    void updateMovie() {
        // it works when data is added
        Set<Long> ids = new HashSet<>();
        ids.add(12L);
        ids.add(5L);
        Set<MovieCategory> categories = movieCategoryRepository.findAllByIdIn(ids);
        Movie movie  = new Movie(6L, "Wieloryb", "A reclusive, morbidly obese English teacher attempts to reconnect " +
                "with his estranged teenage daughter.", "Darren Aronofsky",
                "20th Century Studios", 1666125417L, 156L, 6L, "https://pelnasala.pl/wp-content/uploads/2022/11/Menu_poster.jpg",
                "https://www.imdb.com/video/vi3154887193/?ref_=tt_vi_i_2", 1665909417L, 1671568617L, categories);
        Object resp = movieService.updateMovie(movie, 6L);
        Object expected = Utils.response(movie, "Successful update room");
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @Order(4)
    void uploadPoster() {
        // it works when data is added
        Object resp = movieService.uploadPoster(9L, "https://i0.wp.com/500filmow.pl/wp-content/uploads/2022/04/northmanposter-1.jpg");
        Movie movie = movieRepository.findById(9L).get();
        movie.setPosterPhoto("https://i0.wp.com/500filmow.pl/wp-content/uploads/2022/04/northmanposter-1.jpg");
        Object expected = Utils.response(movie, "Successfully added poster");
        assertThat(resp).usingRecursiveComparison().isEqualTo(expected);
    }

}
