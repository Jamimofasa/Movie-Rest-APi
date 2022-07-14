package com.James_Morand.MovieRestAPI.Contoller;


import com.James_Morand.MovieRestAPI.Movie.Movie;
import com.James_Morand.MovieRestAPI.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieRestController {

    @Autowired
    private MovieService movieService ;

    //Get all movies
    @GetMapping("/movies")
    public List<Movie> getAllMovie()
    {
        return movieService.getAllMovies();
    }

    //Get one movie
    @GetMapping("/movies/{id}")
    public Movie getOneMovie(@PathVariable int id)
    {
        return movieService.getMovie(id);
    }

    //Add a movie
    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie){

        return movieService.addMovie(movie);
    }



    //Update movie info
    @PutMapping("/movies/{id}")
    public Movie updateMovie(@RequestBody Movie movie,@PathVariable int id)
    {

        return movieService.updateMovie(movie,id);

    }

    //Delete movie
    @DeleteMapping("/movies/{id}")
    public Movie deleteMovie(@PathVariable int id)
    {
        return movieService.deleteMovie(id);
    }


}
