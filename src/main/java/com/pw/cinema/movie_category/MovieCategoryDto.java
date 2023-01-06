package com.pw.cinema.movie_category;

import com.pw.cinema.movie.Movie;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieCategoryDto {

    @Id
    private Long id;
    private String name;
    private String slug;
    private Set<Movie> movies;

}
