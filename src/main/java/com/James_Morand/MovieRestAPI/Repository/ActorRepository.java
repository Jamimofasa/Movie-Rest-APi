package com.James_Morand.MovieRestAPI.Repository;

import com.James_Morand.MovieRestAPI.Movie.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    List<Actor> findByNameContainingIgnoreCase(String name);

    // Exact match (case-insensitive) used for duplicate checking
    boolean existsByNameIgnoreCase(String name);
}
