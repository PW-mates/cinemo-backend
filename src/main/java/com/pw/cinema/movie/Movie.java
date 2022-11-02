package com.pw.cinema.movie;

import com.pw.cinema.movie_category.MovieCategory;
import lombok.NonNull;

import javax.persistence.*;
import javax.xml.catalog.Catalog;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "movie_has_categories",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_category_id"))
    private Set<MovieCategory> movieCategories = new HashSet<>();


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

    public Set<MovieCategory> getMovieCategories() {
        return movieCategories;
    }

    public void setMovieCategories(Set<MovieCategory> movieCategories) {
        this.movieCategories = movieCategories;
        movieCategories.forEach(movieCategory -> movieCategory.getMovies().add(this));
    }

    public void addCategory(MovieCategory movieCategory) {
        this.movieCategories.add(movieCategory);
        movieCategory.getMovies().add(this);
    }

    public void removeCategory(Long categoryId) {
        MovieCategory movieCategory =
                this.movieCategories.stream().filter(mc -> Objects.equals(mc.getId(), categoryId)).findFirst().orElse(null);
        if (movieCategory != null) {
            this.movieCategories.remove(movieCategory);
            movieCategory.getMovies().remove(this);
        }

    }
}
