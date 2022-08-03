package com.James_Morand.MovieRestAPI.Service;

import com.James_Morand.MovieRestAPI.Movie.Movie;
import com.James_Morand.MovieRestAPI.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


@Component
public class MovieService  {

    @Autowired
    private MovieRepository movieRepository;

    public Random random;
    public static List<Movie> moviesList = new ArrayList<>();
    private int idCount =moviesList.size();


//populate movielist
    static {

        Movie m = new Movie(1,"Avengers: Age of Ultron",141 ,"5/2/25" , 495.2, null);

        moviesList.add(m);
        moviesList.add(new Movie(2,"Star Wars: Episode IV - A New Hope",121 ,"May 25, 1977" , 11, null));
        moviesList.add(new Movie(3,"Blazing Saddles",93 ,"February 7, 1974" , 119.6, null));

    }

    //Get all movies
    public List<Movie> getAllMovies()
    {
        return moviesList;
    }

    //Get one movie
    public Movie getMovie(int id)
    {
        Iterator<Movie> iterator = moviesList.iterator();

        while (iterator.hasNext())
        {
            Movie movie = iterator.next();
            if (movie.getId() == id)
                return movie;

        }

        return null;
    }

    //add a movie
    public Movie addMovie(Movie movie)
    {

        movie.setId(++idCount);


        //Add Movie to Repository
        movieRepository.save(movie);

        //Add Movie to List for postman
//        moviesList.add(movie);
        return movie;
    }

    //Delete movie
    public Movie deleteMovie(int id)
    {
        Iterator<Movie> iterator = moviesList.iterator();

        while (iterator.hasNext())
        {
            Movie movie = iterator.next();
            if (movie.getId() == id) {
                iterator.remove();
                //Delete from repository
                movieRepository.delete(movie);
                return movie;
            }
        }

        return null;
    }

    //Update movie
    public Movie updateMovie(Movie movie, int id)
    {
        Iterator<Movie> iterator = moviesList.iterator();

        while (iterator.hasNext())
        {
            Movie movie_ = iterator.next();
            if (movie_.getId() == id) {
                movie_.setMovieLength(movie.getMovieLength());
                movie_.setName(movie.getName());
                movie_.setCostToMake(movie.getCostToMake());
                movie_.setReleaseDate(movie.getReleaseDate());

                return movie_;
            }

        }

        movie.setId(id);

        return movie;
    }


}
