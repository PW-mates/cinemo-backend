package com.pw.cinema.movie_category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCategoryRepository extends JpaRepository<MovieCategory, Long> {
    MovieCategory findByName(String name);
//    List<MovieCategory> findAllByMoviesId(Long movieId);
    List<MovieCategory> findMovieCategoriesByMoviesId(Long movieId);

}
