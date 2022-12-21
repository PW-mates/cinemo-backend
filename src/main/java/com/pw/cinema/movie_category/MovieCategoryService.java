package com.pw.cinema.movie_category;

import com.pw.cinema.exceptions.AlreadyExistsException;
import com.pw.cinema.movie.Movie;
import com.pw.cinema.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieCategoryService {

    @Autowired
    private MovieCategoryRepository movieCategoryRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Object getCategory(MovieCategory movieCategory) {
        MovieCategory movieCategory1 = movieCategoryRepository.findById(movieCategory.getId()).get();
        Map<String, Object> response = new HashMap<>();
        response.put("data", movieCategory1);
        response.put("message", "Successfully found movies");
        response.put("success", true);
        return response;
    }

    public Object getAllCategories() {
        List<MovieCategory> movieCategoryList = movieCategoryRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("data", movieCategoryList);
        response.put("message", "Successfully found movies");
        response.put("success", true);
        return response;
    }


    public Object create(MovieCategory movieCategory) throws AlreadyExistsException {
        if (movieCategoryRepository.findByName(movieCategory.getName()) != null) {
            throw new AlreadyExistsException("Movie with that name already exists");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("data", movieCategoryRepository.save(movieCategory));
        response.put("message", "Successfully added new movie");
        response.put("success", true);
        return response;
    }


    public Object updateCategory(MovieCategory categoryChanges, Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", movieCategoryRepository.save(categoryChanges));
        response.put("message", "Successfully found movies");
        response.put("success", true);
        return response;
    }

    public Object deleteCategory(Long id) {
        Map<String, Object> response = new HashMap<>();
        movieCategoryRepository.deleteById(id);
        response.put("message", "Successfully deleted movie");
        response.put("success", true);
        return response;
    }

    public Object getAllMoviesByCategoryId(Long categoryId) {
        List <Movie> movies = movieRepository.findMoviesByMovieCategoriesId(categoryId);
        return movies;
    }

    public Object getAllMovieCategoriesByMovieId(Long movieId) {
        List <MovieCategory> movieCategories = movieCategoryRepository.findMovieCategoriesByMoviesId(movieId);
        return movieCategories;
    }

}
