package com.pw.cinema.movie;

import com.pw.cinema.movie_category.MovieCategory;
import com.pw.cinema.movie_category.MovieCategoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.pw.cinema.utils.Utils.response;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieCategoryRepository movieCategoryRepository;
    @Autowired
    ModelMapper modelMapper;

    public MovieService(MovieRepository movieRepository, MovieCategoryRepository movieCategoryRepository) {
        this.movieRepository = movieRepository;
        this.movieCategoryRepository = movieCategoryRepository;
    }

    public Object create(Movie movie) {
        Set<Long> movieCategoryIds =
                movie.getCategories().stream().map(MovieCategory::getId).collect(Collectors.toSet());
        if (!movieCategoryRepository.existsAllByIdIn(movieCategoryIds))
            throw new NoSuchElementException("Not found categories.");
        movie.setCategories(movieCategoryRepository.findAllByIdIn(movieCategoryIds));
        Movie newMovie = movieRepository.save(movie);
        return response(newMovie, "Successfully added new movie");
    }

    public Object getMovieList() {
        List<Movie> movies = movieRepository.findAll();
        return response(movies, "Successfully found movies");
    }

    public Object updateMovie(Movie updatedMovie, Long id) {
        if (!movieRepository.existsById(id))
            throw new NoSuchElementException("Not found movie with this id");
        Set<Long> movieCategoryIds =
                updatedMovie.getCategories().stream().map(MovieCategory::getId).collect(Collectors.toSet());
        if (!movieCategoryRepository.existsAllByIdIn(movieCategoryIds))
            throw new NoSuchElementException("Not found categories.");
        updatedMovie.setId(id);
        updatedMovie.setCategories(movieCategoryRepository.findAllByIdIn(movieCategoryIds));
        Movie savedMovie = movieRepository.save(updatedMovie);
        return response(savedMovie, "Successfully found movies");
    }

    public Object deleteMovie(Long id) {
        if (!movieRepository.existsById(id))
            throw new NoSuchElementException("Not found movie with this id");
        movieRepository.deleteById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully deleted movie");
        response.put("success", true);
        return response;
    }

    public Object getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No movie present with " +
                "id " + id));
        return response(movie, "Successfully found movie");
    }


    public Object uploadPoster(Long id, String poster) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found movie"));
        movie.setPosterPhoto(poster);
        Movie savedMovie = movieRepository.save(movie);
        return response(savedMovie, "Successfully added poster");
    }
}
