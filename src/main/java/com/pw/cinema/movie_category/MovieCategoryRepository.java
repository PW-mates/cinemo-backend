package com.pw.cinema.movie_category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface MovieCategoryRepository extends JpaRepository<MovieCategory, Long> {
    MovieCategory findByName(String name);
    Boolean existsAllByIdIn(Set<Long> ids);
    Set<MovieCategory> findAllByIdIn(Set<Long> ids);
}
