package com.pw.cinema.movie_category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieCategoryRepository extends JpaRepository<MovieCategory, Long> {
    MovieCategory findByName(String name);
}
