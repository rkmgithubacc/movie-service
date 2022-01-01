package com.rkmgithubacc.mba.moviems.model;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id", precision = 10)
    private int movieId;

    @Column(name = "movie_name", unique = true)
    @Size(max = 50)
    @NotNull(message = "Movie name must not be null")
    private String movieName;

    @Column(name = "movie_desc")
    @Size(max = 500)
    @NotNull(message = "Movie description must not be null")
    private String movieDescription;

    @Column(name = "release_date")
    @NotNull(message = "Release Date must not be null")
    private LocalDate releaseDate;

    @Column(name = "duration", precision = 3)
    @NotNull(message = "Duration must not be null")
    @Check(constraints = "duration > 60")
    private int duration;

    @Column(name = "cover_photo_url")
    @Size(max = 500)
    @NotNull(message = "Cover Photo URL must not be null")
    private String coverPhotoUrl;

    @Column(name = "trailer_url")
    @Size(max = 500)
    @NotNull(message = "Trailer URL must not be null")
    private String trailerUrl;

    public Movie() {
    }

    public Movie(String movieName, String movieDescription, LocalDate releaseDate, int duration, String coverPhotoUrl, String trailerUrl) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.coverPhotoUrl = coverPhotoUrl;
        this.trailerUrl = trailerUrl;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCoverPhotoUrl() {
        return coverPhotoUrl;
    }

    public void setCoverPhotoUrl(String coverPhotoUrl) {
        this.coverPhotoUrl = coverPhotoUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieDescription='" + movieDescription + '\'' +
                ", releaseDate=" + releaseDate +
                ", duration=" + duration +
                ", coverPhotoUrl='" + coverPhotoUrl + '\'' +
                ", trailerUrl='" + trailerUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return movieId == movie.movieId;
    }

    @Override
    public int hashCode() {
        return movieId;
    }
}
