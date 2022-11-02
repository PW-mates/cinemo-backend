package com.pw.cinema.movie;

import com.pw.cinema.exceptions.AlreadyExistsException;
import com.pw.cinema.movie_category.MovieCategory;
import com.pw.cinema.movie_category.MovieCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Object getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).get();
        Map<String, Object> response = new HashMap<>();
        response.put("data", movie);
        response.put("message", "Successfully found movie");
        response.put("success", true);
        return response;
    }

    public Object create(Movie movie) throws AlreadyExistsException {
        if (movieRepository.findByName(movie.getTitle()) != null) {
            throw new AlreadyExistsException("Movie with that name already exists");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("data", movieRepository.save(movie));
        response.put("message", "Successfully added new movie");
        response.put("success", true);
        return response;
    }

    public Object getMovieList() {
        List<Movie> movies = new ArrayList<>(movieRepository.findAll());
        Map<String, Object> response = new HashMap<>();
        response.put("data", movies);
        response.put("message", "Successfully found movies");
        response.put("success", true);
        return response;
    }

    public Object updateMovie(Movie movieChanges, Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", movieRepository.save(movieChanges));
        response.put("message", "Successfully found movies");
        response.put("success", true);
        return response;
    }

    public Object deleteMovie(Long id) {
        Map<String, Object> response = new HashMap<>();
        movieRepository.deleteById(id);
        response.put("message", "Successfully deleted movie");
        response.put("success", true);
        return response;
    }
//
//    public Object addCategoryToMovie(Long movieId, Long categoryId) {
//        Movie movie = movieRepository.findById(movieId).get();
//        MovieCategory movieCategory = movieCategoryRepository.findById(categoryId).get();
//        movie.addCategory(movieCategory);
//        return movieRepository.save(movie);
//    }
}
