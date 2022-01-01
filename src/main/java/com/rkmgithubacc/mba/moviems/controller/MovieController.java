package com.rkmgithubacc.mba.moviems.controller;

import com.rkmgithubacc.mba.moviems.dto.MovieBookingDTO;
import com.rkmgithubacc.mba.moviems.model.Movie;
import com.rkmgithubacc.mba.moviems.dto.MovieDTO;
import com.rkmgithubacc.mba.moviems.model.Theatre;
import com.rkmgithubacc.mba.moviems.model.User;
import com.rkmgithubacc.mba.moviems.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/movie_service/v1")
public class MovieController {
    private final MovieService movieService;
    private final ModelMapper modelMapper;

    public MovieController(MovieService movieService, ModelMapper modelMapper) {
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    //Register/ create movie record
    @PostMapping(value = "/movies", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO movieDTO) {
        Movie newMovie = modelMapper.map(movieDTO, Movie.class);
        Movie savedMovie = movieService.acceptMovieDetails(newMovie);
        MovieDTO savedMovieDTO = modelMapper.map(savedMovie, MovieDTO.class);

        return new ResponseEntity<>(savedMovieDTO, HttpStatus.CREATED);
    }

    //Get all movies
    @GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<Movie> moviesList = movieService.getAllMovies();
        List<MovieDTO> moviesDTOList = new ArrayList<>();
        for(Movie movie: moviesList) {
            moviesDTOList.add(modelMapper.map(movie, MovieDTO.class));
        }

        return new ResponseEntity<>(moviesDTOList, HttpStatus.OK);
    }

    //Get a single movie based on ID
    @GetMapping(value = "/movies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> getMovieForId(@PathVariable(name = "id") int id) {
        Movie retrievedMovie = movieService.getMovieDetails(id);
        MovieDTO retrievedMovieDTO = modelMapper.map(retrievedMovie, MovieDTO.class);

        return new ResponseEntity<>(retrievedMovieDTO, HttpStatus.OK);
    }

    //Update movie details
    @PutMapping(value = "/movies/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> updateMovieForId(@PathVariable(name = "id") int id,
                                                     @RequestBody MovieDTO movieDTO) {
        Movie movieToUpdate = modelMapper.map(movieDTO, Movie.class);
        Movie updatedMovie = movieService.updateMovieDetails(id, movieToUpdate);
        MovieDTO updatedMovieDTO = modelMapper.map(updatedMovie, MovieDTO.class);

        return new ResponseEntity<>(updatedMovieDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/movies/{id}")
    public ResponseEntity<Boolean> deleteMovie(@PathVariable(name = "id") int id) {
        Boolean deleteStatus = movieService.deleteMovie(id);

        return new ResponseEntity<>(deleteStatus, HttpStatus.OK);
    }

    @PostMapping(value = "/movies/booking" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity bookMovieDetails(@RequestBody MovieBookingDTO movieBookingDTO){
        Movie requestedMovie = modelMapper.map(movieBookingDTO.getMovie(),Movie.class);
        User fromUser = modelMapper.map(movieBookingDTO.getUser(),User.class);
        Theatre requestedTheatre = modelMapper.map(movieBookingDTO.getTheatre(), Theatre.class);

        boolean isValidBooking = movieService.bookMovie(fromUser,requestedMovie,requestedTheatre);

        if(!isValidBooking)
            return new ResponseEntity("Not Booked !!", HttpStatus.OK) ;
        return new ResponseEntity("Booked Successfully !!", HttpStatus.OK) ;
    }
}
