package com.pw.cinema.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByName(String name);
    List<Movie> findMoviesByMovieCategoriesId(Long movieCategoryId);
}
