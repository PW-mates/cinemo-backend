package com.pw.cinema.movie_category;

import com.pw.cinema.exceptions.AlreadyExistsException;
import com.pw.cinema.exceptions.HasMoviesException;
import com.pw.cinema.movie.Movie;
import com.pw.cinema.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieCategoryService {

    private final MovieCategoryRepository movieCategoryRepository;

    public MovieCategoryService(MovieCategoryRepository movieCategoryRepository) {
        this.movieCategoryRepository = movieCategoryRepository;
    }

    public Object getCategory(Long id) {
        MovieCategory movieCategory1 = movieCategoryRepository.findById(id).get();
        Map<String, Object> response = new HashMap<>();
        response.put("data", movieCategory1);
        response.put("message", "Successfully found category");
        response.put("success", true);
        return response;
    }

    public Object getAllCategories() {
        List<MovieCategory> movieCategoryList = movieCategoryRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("data", movieCategoryList);
        response.put("message", "Successfully found all categories");
        response.put("success", true);
        return response;
    }


    public Object create(MovieCategory movieCategory) throws AlreadyExistsException {
        if (movieCategoryRepository.findByName(movieCategory.getName()) != null) {
            throw new AlreadyExistsException("Movie with that name already exists");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("data", movieCategoryRepository.save(movieCategory));
        response.put("message", "Successfully added new category");
        response.put("success", true);
        return response;
    }


    public Object updateCategory(MovieCategory categoryChanges, Long id) {
        MovieCategory movieCategory = movieCategoryRepository.findById(id).get();
        movieCategory.setName(categoryChanges.getName());
        movieCategory.setSlug(categoryChanges.getSlug());
        // movies add or change
        Map<String, Object> response = new HashMap<>();
        response.put("data", movieCategoryRepository.save(movieCategory));
        response.put("message", "Successfully updated category");
        response.put("success", true);
        return response;
    }

    public Object deleteCategory(Long id) throws HasMoviesException {
        Map<String, Object> response = new HashMap<>();
        MovieCategory movieCategory = movieCategoryRepository.findById(id).get();
        boolean isUsed = movieCategory.getMovies().size() > 0;
        if (isUsed) {
            throw new HasMoviesException("This category can not be deleted, because it includes movies");
        }
        movieCategoryRepository.deleteById(id);
        response.put("message", "Successfully deleted this category");
        response.put("success", true);
        return response;
    }

}
