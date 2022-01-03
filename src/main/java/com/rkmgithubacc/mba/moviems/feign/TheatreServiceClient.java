package com.rkmgithubacc.mba.moviems.feign;

import com.rkmgithubacc.mba.moviems.model.Theatre;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "theatre-service-client", url = "http://localhost:8082")
//@FeignClient(name = "theatre-service-client", url = "${theatreService.address}")
@FeignClient(name = "THEATRE-SERVICE")
public interface TheatreServiceClient {
    @GetMapping(value = "${theatreService.bookMoviePath}")
//    @RequestMapping(value = "/theatre_service/v1/theatres/{theatreId}/movie/{movieId}", method = RequestMethod.GET)
    Theatre getTheatre(@PathVariable(name = "theatreId") int theatreId,
                       @PathVariable(name = "movieId") int movieId);
}
