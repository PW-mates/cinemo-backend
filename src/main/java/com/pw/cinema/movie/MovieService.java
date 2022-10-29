package com.pw.cinema.movie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        return movie;
    }
}
