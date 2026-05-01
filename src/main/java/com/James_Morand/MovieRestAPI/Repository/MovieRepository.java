package com.James_Morand.MovieRestAPI.Repository;

import com.James_Morand.MovieRestAPI.Movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
