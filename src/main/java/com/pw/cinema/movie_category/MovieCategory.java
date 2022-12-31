package com.pw.cinema.movie_category;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pw.cinema.movie.Movie;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "movie_category")
public class MovieCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String slug;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            },
            mappedBy = "category")
    @JsonIgnore
    private Set<Movie> movies = new HashSet<>();


    public MovieCategory() {
    }

    public MovieCategory(Long id) {
        this.id = id;
    }
}
