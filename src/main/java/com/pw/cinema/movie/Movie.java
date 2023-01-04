package com.pw.cinema.movie;

import com.pw.cinema.movie_category.MovieCategory;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_has_categories",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_category_id"))
    private Set<MovieCategory> categories = new HashSet<>();

}
