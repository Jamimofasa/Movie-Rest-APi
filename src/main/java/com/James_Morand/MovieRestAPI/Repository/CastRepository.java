package com.James_Morand.MovieRestAPI.Repository;

import com.James_Morand.MovieRestAPI.Movie.Cast;
import com.James_Morand.MovieRestAPI.Movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastRepository extends JpaRepository<Cast, Integer> {
}
