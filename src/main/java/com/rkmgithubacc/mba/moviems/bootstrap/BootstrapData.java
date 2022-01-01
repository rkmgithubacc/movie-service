package com.rkmgithubacc.mba.moviems.bootstrap;

import com.rkmgithubacc.mba.moviems.dao.MovieDAO;
import com.rkmgithubacc.mba.moviems.model.Movie;
import com.rkmgithubacc.mba.moviems.service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {
    private final MovieService movieService;

    public BootstrapData(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started through bootstrap");
        System.out.println("Starting movie entity interaction using movie service:" + movieService);

        List<Movie> movieList = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1.setMovieName("Avengers: The Infinity War");
        movie1.setMovieDescription("Thanos defeats Avengers");
        movie1.setReleaseDate(LocalDate.of(2016, 4, 2));
        movie1.setDuration(150);
        movie1.setCoverPhotoUrl("Cover photo URL1");
        movie1.setTrailerUrl("Trailer URL1");

        Movie savedMovie1 = movieService.acceptMovieDetails(movie1);
        System.out.println("Saved movie: " + savedMovie1);

        Movie movie2 = new Movie();
        movie2.setMovieName("Avengers: The End Game");
        movie2.setMovieDescription("Avengers defeat Thanos");
        movie2.setReleaseDate(LocalDate.of(2018, 8, 1));
        movie2.setDuration(180);
        movie2.setCoverPhotoUrl("Cover photo URL2");
        movie2.setTrailerUrl("Trailer URL2");
        movieList.add(movie2);

        Movie movie3 = new Movie();
        movie3.setMovieName("Avengers: The Age of Ultron");
        movie3.setMovieDescription("Ultron is born");
        movie3.setReleaseDate(LocalDate.of(2014, 4, 25));
        movie3.setDuration(140);
        movie3.setCoverPhotoUrl("Cover photo URL3");
        movie3.setTrailerUrl("Trailer URL3");
        movieList.add(movie3);

        List<Movie> savedMovieList = movieService.acceptMultipleMovieDetails(movieList);
        System.out.println("List of movies saved: " + savedMovieList.size());
    }
}
