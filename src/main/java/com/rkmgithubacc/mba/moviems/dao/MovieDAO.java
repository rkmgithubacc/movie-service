package com.rkmgithubacc.mba.moviems.dao;

import com.rkmgithubacc.mba.moviems.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface demonstrates Factory Pattern and
 * represents the data access layer (DAL) using Spring Data JPA approach.
 * It also implements derived queries
 */
public interface MovieDAO extends JpaRepository<Movie, Integer> {
    List<Movie> findByMovieName(String movieName);
    List<Movie> findByMovieNameAndDuration(String name, int duration);
    List<Movie> findByReleaseDateBetween(LocalDate start, LocalDate end);
    List<Movie> findByDurationGreaterThanEqual(int duration);
    List<Movie> findByReleaseDateAfter(LocalDate releaseDate);
    List<Movie> findByMovieNameContaining(String movieName);
    List<Movie> findByMovieNameIgnoreCase(String movieName);
}
