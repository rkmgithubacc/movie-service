package com.rkmgithubacc.mba.moviems.aspect;

import com.rkmgithubacc.mba.moviems.feign.TheatreServiceClient;
import com.rkmgithubacc.mba.moviems.model.Movie;
import com.rkmgithubacc.mba.moviems.model.Theatre;
import com.rkmgithubacc.mba.moviems.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TheatreAspect {
    TheatreServiceClient theatreServiceClient;
    
    public TheatreAspect(TheatreServiceClient theatreServiceClient) {
        this.theatreServiceClient = theatreServiceClient;
    }
    
    @Before(value = "execution(* com.rkmgithubacc.mba.moviems.service.MovieServiceImpl.bookMovie(..)) " +
            "&& args(user, movie, theatre)")
    public void beforeAdvice(JoinPoint joinPoint, User user, Movie movie, Theatre theatre) {
        Theatre receivedTheatre = theatreServiceClient.getTheatre(theatre.getTheatreId(), theatre.getMovieId());
        if (receivedTheatre == null) {
            throw new RuntimeException("The chosen theatre-movie combination is not available!");
        }
    }
}
