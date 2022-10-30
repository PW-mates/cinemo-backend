package com.pw.cinema.movie;

import com.pw.cinema.movie_category.MovieCategory;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private Long length;
    private String ageRestriction;
    @NonNull
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "movie_has_categories",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_category_id", referencedColumnName = "id"))
    private List<MovieCategory> movieCategories;


    public Movie() {
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", length='" + length + '\'' +
                ", ageRestriction='" + ageRestriction + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MovieCategory> getMovieCategories() {
        return movieCategories;
    }

    public void setMovieCategories(List<MovieCategory> movieCategories) {
        this.movieCategories = movieCategories;
    }
}
