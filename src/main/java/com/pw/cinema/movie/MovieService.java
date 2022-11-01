package com.pw.cinema.movie;

import com.pw.cinema.exceptions.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (movieRepository.findByName(movie.getName()) != null) {
            throw new AlreadyExistsException("Movie with that name already exists");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("data", movieRepository.save(movie));
        response.put("message", "Successfully added new movie");
        response.put("success", true);
        return response;
    }


    public Object getMovieList() {
        List<Movie> movies = movieRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("data", movies);
        response.put("message", "Successfully found movies");
        response.put("success", true);
        return response;
    }

    public Object updateMovie(Movie movieChanges, Long id) {
        Movie movie = movieRepository.findById(id).get();
        movie.setName(movieChanges.getName());
        movie.setDescription(movieChanges.getDescription());
        movie.setLength(movieChanges.getLength());
        movie.setAgeRestriction(movieChanges.getAgeRestriction());
        Map<String, Object> response = new HashMap<>();
        response.put("data", movieRepository.save(movie));
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
}
