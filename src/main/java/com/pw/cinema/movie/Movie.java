package com.pw.cinema.movie;

import com.pw.cinema.movie_category.MovieCategory;
import lombok.NonNull;

import javax.persistence.*;
import javax.xml.catalog.Catalog;
import java.util.*;

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
    private Date releaseDate;
    private Long duration;
    private Long rating;
    private String posterPhoto;
    private String trailerUrl;
    private Date showingFrom;
    private Date showingTo;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "movie_has_categories",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_category_id"))
    private Set<MovieCategory> category = new HashSet<>();


    public Movie() {
    }

    @Override
    public String toString() {
        return "Movie";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getPosterPhoto() {
        return posterPhoto;
    }

    public void setPosterPhoto(String posterPhoto) {
        this.posterPhoto = posterPhoto;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public Date getShowingFrom() {
        return showingFrom;
    }

    public void setShowingFrom(Date showingFrom) {
        this.showingFrom = showingFrom;
    }

    public Date getShowingTo() {
        return showingTo;
    }

    public void setShowingTo(Date showingTo) {
        this.showingTo = showingTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<MovieCategory> getCategory() {
        return category;
    }

    public void addCategories(Set<MovieCategory> newCategory) {
        Set<MovieCategory> categorySet = null;

        this.category.addAll(newCategory);
//        movieCategory.getMovies().add(this);
//        movieCategories.forEach(movieCategory -> movieCategory.getMovies().add(this));
    }
//
//    public void removeCategory(Long categoryId) {
//        MovieCategory movieCategory =
//                this.movieCategories.stream().filter(mc -> Objects.equals(mc.getId(), categoryId)).findFirst().orElse(null);
//        if (movieCategory != null) {
//            this.movieCategories.remove(movieCategory);
//            movieCategory.getMovies().remove(this);
//        }
//
//    }
}
