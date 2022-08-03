package com.James_Morand.MovieRestAPI.Repository;

import com.James_Morand.MovieRestAPI.Movie.Cast;
import com.James_Morand.MovieRestAPI.Movie.Movie;
import org.springframework.data.repository.CrudRepository;

public interface CastRepository extends CrudRepository<Cast, Integer> {
}
