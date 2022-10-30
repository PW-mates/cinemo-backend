package com.pw.cinema.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Object getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).get();
        Map <String, Object> response = new HashMap<>();
        response.put("data", movie);
        response.put("message", "Successfully found movie");
        response.put("success", true);
        return response;
    }
}
