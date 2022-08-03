package com.James_Morand.MovieRestAPI.Contoller;


import com.James_Morand.MovieRestAPI.Movie.Movie;
import com.James_Morand.MovieRestAPI.Repository.MovieRepository;
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
    @GetMapping("/movie/{id}")
    public Movie getMovie(@PathVariable int id)
    {
        Movie movie = movieService.getMovie(id);

        if (movie == null)
            throw new RuntimeException(String.format("No Movie %d Found", id));

        return movie;
    }

    //Add a movie
    @PostMapping("/movie")
    public Movie addMovie(@RequestBody Movie movie)
    {
        return movieService.addMovie(movie);
    }

    //Update movie info
    @PutMapping("/movie/{id}")
    public Movie updateMovie(@RequestBody Movie movie,@PathVariable int id)
    {
        return movieService.updateMovie(movie,id);
    }

    //Delete movie
    @DeleteMapping("/movie/{id}")
    public void deleteMovie(@PathVariable int id)
    {
        if (movieService.deleteMovie(id) == null)
            throw new RuntimeException(String.format("No Movie %d Found", id));
    }


}
