package com.pw.cinema.movie;

import com.pw.cinema.movie_category.MovieCategory;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Id;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class MovieDto {

    @Id
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String director;
    @NotNull
    private String distributor;
    @NotNull
    private Date releaseDate;
    @NotNull
    private Long duration;
    @NotNull
    private Long rating;
    @NotNull
    private String posterPhoto;
    @NotNull
    private String trailerUrl;
    @NotNull
    private Date showingFrom;
    @NotNull
    private Date showingTo;
    @NotNull
    private Set<MovieCategory> categories;
}
