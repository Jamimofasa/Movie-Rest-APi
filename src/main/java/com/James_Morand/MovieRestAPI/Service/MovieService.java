package com.James_Morand.MovieRestAPI.Service;

import com.James_Morand.MovieRestAPI.Exception.ResourceNotFoundException;
import com.James_Morand.MovieRestAPI.Movie.Movie;
import com.James_Morand.MovieRestAPI.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovie(int id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
    }

    public Movie addMovie(Movie movie) {
        // Ensure id is not set manually — let JPA generate it
        movie.setId(0);
        return movieRepository.save(movie);
    }

    public Movie updateMovie(int id, Movie updatedMovie) {
        Movie existing = getMovie(id); // throws if not found
        existing.setName(updatedMovie.getName());
        existing.setMovieLength(updatedMovie.getMovieLength());
        existing.setReleaseDate(updatedMovie.getReleaseDate());
        existing.setCostToMake(updatedMovie.getCostToMake());
        existing.setCast(updatedMovie.getCast());
        return movieRepository.save(existing);
    }

    public void deleteMovie(int id) {
        Movie movie = getMovie(id); // throws if not found
        movieRepository.delete(movie);
    }

}
