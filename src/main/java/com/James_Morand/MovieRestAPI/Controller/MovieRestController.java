package com.James_Morand.MovieRestAPI.Controller;


import com.James_Morand.MovieRestAPI.Movie.Movie;
import com.James_Morand.MovieRestAPI.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Movie> getMovie(@PathVariable int id)
    {
        Movie movie = movieService.getMovie(id);
        return ResponseEntity.ok(movie);
    }

    //Add a movie
    @PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie)
    {
        return ResponseEntity.ok(movieService.addMovie(movie));
    }

    //Update movie info
    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie,@PathVariable int id)
    {
        return ResponseEntity.ok(movieService.updateMovie(id, movie));
    }

    //Delete movie
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id)
    {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }


}
