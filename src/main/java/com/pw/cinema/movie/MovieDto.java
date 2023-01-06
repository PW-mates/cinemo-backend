package com.pw.cinema.movie;

import com.pw.cinema.movie_category.MovieCategory;
import lombok.*;

import javax.persistence.Id;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class MovieDto {

    @Id
    private Long id;
    private String title;
    private String description;
    private String director;
    private String distributor;
    private Long releaseDate;
    private Long duration;
    private Long rating;
    private String posterPhoto;
    private String trailerUrl;
    private Long showingFrom;
    private Long showingTo;
    private Set<MovieCategory> categories;
}
