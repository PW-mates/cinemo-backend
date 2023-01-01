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
    @NotNull
    private String name;
    @NotNull
    private String slug;
    @NotNull
    private Set<Movie> movies;

}
