package com.rkmgithubacc.mba.moviems.service;

import com.rkmgithubacc.mba.moviems.model.Movie;
import com.rkmgithubacc.mba.moviems.model.Theatre;
import com.rkmgithubacc.mba.moviems.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    Movie acceptMovieDetails(Movie movie);

    List<Movie> acceptMultipleMovieDetails(List<Movie> movies);

    Movie getMovieDetails(int id) ;

    Movie updateMovieDetails(int id, Movie movie);

    boolean deleteMovie(int id);

    List<Movie> getAllMovies();

    Page<Movie> getPaginatedMovieDetails(Pageable pageRequest);

    Boolean bookMovie(User user, Movie movie, Theatre theatre);
}
