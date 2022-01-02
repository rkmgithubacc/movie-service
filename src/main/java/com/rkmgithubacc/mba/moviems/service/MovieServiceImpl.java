package com.rkmgithubacc.mba.moviems.service;

import com.rkmgithubacc.mba.moviems.dao.MovieDAO;
import com.rkmgithubacc.mba.moviems.feign.TheatreServiceClient;
import com.rkmgithubacc.mba.moviems.model.Movie;
import com.rkmgithubacc.mba.moviems.model.Theatre;
import com.rkmgithubacc.mba.moviems.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDAO movieDAO;
    private final RestTemplate restTemplate;
    private final TheatreServiceClient theatreServiceClient;
    
    @Value("${userService.url}")
    private String userAppUrl;
    
    @Value("${theatreService.url}")
    private String theatreAppUrl;
    
    public MovieServiceImpl(MovieDAO movieDAO, RestTemplate restTemplate, TheatreServiceClient theatreServiceClient) {
        this.movieDAO = movieDAO;
        this.restTemplate = restTemplate;
        this.theatreServiceClient = theatreServiceClient;
    }
    
    @Override
    public Movie acceptMovieDetails(Movie movie) {
        return movieDAO.save(movie);
    }
    
    @Override
    @Transactional
    public List<Movie> acceptMultipleMovieDetails(List<Movie> moviesList) {
        List<Movie> savedMoviesList = new ArrayList<>();
        for (Movie movie : moviesList) {
            savedMoviesList.add(acceptMovieDetails(movie));
        }
        return savedMoviesList;
    }
    
    @Override
    public Movie getMovieDetails(int id) {
        return
                movieDAO.findById(id).isPresent() ? movieDAO.findById(id).get() : new Movie();
    }
    
    @Override
    public Movie updateMovieDetails(int id, Movie movie) {
        Movie savedMovie = getMovieDetails(id);
        savedMovie.setMovieName(movie.getMovieName());
        savedMovie.setMovieDescription(movie.getMovieDescription());
        savedMovie.setDuration(movie.getDuration());
        savedMovie.setReleaseDate(movie.getReleaseDate());
        savedMovie.setCoverPhotoUrl(movie.getCoverPhotoUrl());
        savedMovie.setTrailerUrl(movie.getTrailerUrl());
        return movieDAO.save(savedMovie);
    }
    
    @Override
    public boolean deleteMovie(int id) {
        Movie savedMovie = getMovieDetails(id);
        
        if (savedMovie == null) {
            return false;
        }
        movieDAO.delete(savedMovie);
        return true;
    }
    
    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.findAll();
    }
    
    @Override
    public Page<Movie> getPaginatedMovieDetails(Pageable pageRequest) {
        return movieDAO.findAll(pageRequest);
    }
    
    @Override
    public Boolean bookMovie(User user, Movie movie, Theatre theatre) {
        
        //Check whether requested movie is a valid movie.
        Optional<Movie> requestedMovie = movieDAO.findById(movie.getMovieId());
        if (!requestedMovie.isPresent()) {
            System.out.println("Requested movie not found");
            return false;
        }
        
        //Check whether User is valid
        Map<String, String> userUriMap = new HashMap<>();
        userUriMap.put("id", String.valueOf(user.getUserID()));
        User receivedUser = restTemplate.getForObject(userAppUrl, User.class, userUriMap);
        if (receivedUser == null) {
            System.out.println("Requested user not found");
            return false;
        }
        
        //Check whether theatre and movie combination is valid
//        Map<String, String> theatreUriMap = new HashMap<>();
//        theatreUriMap.put("theatreId", String.valueOf(theatre.getTheatreId()));
//        theatreUriMap.put("movieId", String.valueOf(theatre.getMovieId()));
        //Theatre receivedTheatre = restTemplate.getForObject(theatreAppUrl, Theatre.class, theatreUriMap);
        Theatre receivedTheatre = theatreServiceClient.getTheatre(theatre.getTheatreId(), theatre.getMovieId());
        if (receivedTheatre == null) {
            System.out.println("Requested theatre-movie combination not found");
        }
        return receivedTheatre != null;
    }
}
