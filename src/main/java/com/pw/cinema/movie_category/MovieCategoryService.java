package com.pw.cinema.movie_category;

import com.pw.cinema.exceptions.AlreadyExistsException;
import com.pw.cinema.exceptions.HasMoviesException;
import com.pw.cinema.movie.Movie;
import com.pw.cinema.movie.MovieDto;
import com.pw.cinema.movie.MovieRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.pw.cinema.utils.Utils.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MovieCategoryService {

    private final MovieCategoryRepository movieCategoryRepository;
    final ModelMapper modelMapper;

    public MovieCategoryService(MovieCategoryRepository movieCategoryRepository, ModelMapper modelMapper) {
        this.movieCategoryRepository = movieCategoryRepository;
        this.modelMapper = modelMapper;
    }

    public MovieCategoryDto convertEntityToDto(MovieCategory movieCategory) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(movieCategory, MovieCategoryDto.class);
    }

    public Object getCategory(Long id) {
        MovieCategory movieCategory = movieCategoryRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found category with this id"));
        return response(convertEntityToDto(movieCategory), "Successfully found category");
    }

    public Object getAllCategories() {
        List<MovieCategoryDto> movieCategoryList = movieCategoryRepository.findAll().stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
        return response(movieCategoryList, "Successfully found all categories");
    }

    public Object create(MovieCategory movieCategory) throws AlreadyExistsException {
        if (movieCategoryRepository.findByName(movieCategory.getName()) != null) {
            throw new AlreadyExistsException("Movie category with that name already exists");
        }
        MovieCategory newMovieCategory = movieCategoryRepository.save(movieCategory);
        return response(newMovieCategory, "Successfully added new category");
    }

    public Object updateCategory(MovieCategory categoryChanges, Long id) {
        MovieCategory movieCategory = movieCategoryRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found category with this id"));
        movieCategory.setName(categoryChanges.getName());
        movieCategory.setSlug(categoryChanges.getSlug());
        // movies add or change
        MovieCategory savedCategory = movieCategoryRepository.save(movieCategory);
        return response(convertEntityToDto(savedCategory), "Successfully updated category");
    }

    public Object deleteCategory(Long id) throws HasMoviesException {
        MovieCategory movieCategory = movieCategoryRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Not found category with this id"));
        boolean isUsed = movieCategory.getMovies().size() > 0;
        if (isUsed) {
            throw new HasMoviesException("This category can not be deleted, because it includes movies");
        }
        movieCategoryRepository.deleteById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully deleted this category");
        response.put("success", true);
        return response;
    }

}
