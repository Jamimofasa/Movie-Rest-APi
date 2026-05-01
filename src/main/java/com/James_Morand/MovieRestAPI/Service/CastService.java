package com.James_Morand.MovieRestAPI.Service;

import com.James_Morand.MovieRestAPI.Exception.DuplicateResourceException;
import com.James_Morand.MovieRestAPI.Exception.ResourceNotFoundException;
import com.James_Morand.MovieRestAPI.Movie.Actor;
import com.James_Morand.MovieRestAPI.Movie.Cast;
import com.James_Morand.MovieRestAPI.Repository.ActorRepository;
import com.James_Morand.MovieRestAPI.Repository.CastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CastService {

    @Autowired
    private CastRepository castRepository;

    @Autowired
    private ActorRepository actorRepository;

    public List<Cast> getAllCast() {
        return castRepository.findAll();
    }

    public Cast getCast(int id) {
        return castRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cast not found with id: " + id));
    }

    public Cast addCast(Cast cast) {
        cast.setId(0);
        return castRepository.save(cast);
    }

    public Cast updateCast(int id, Cast updatedCast) {
        Cast existing = getCast(id);
        existing.setActors(updatedCast.getActors());
        return castRepository.save(existing);
    }

    public void deleteCast(int id) {
        castRepository.delete(getCast(id));
    }

    /**
     * Add an existing actor to a cast.
     * Throws DuplicateResourceException if the actor is already in this cast.
     */
    public Cast addActorToCast(int castId, int actorId) {
        Cast cast = getCast(castId);
        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + actorId));

        boolean alreadyInCast = cast.getActors().stream()
                .anyMatch(a -> a.getId() == actorId);

        if (alreadyInCast) {
            throw new DuplicateResourceException(
                "Actor '" + actor.getName() + "' (id: " + actorId + ") is already in cast id: " + castId);
        }

        cast.getActors().add(actor);
        return castRepository.save(cast);
    }

    /**
     * Remove an actor from a cast.
     */
    public Cast removeActorFromCast(int castId, int actorId) {
        Cast cast = getCast(castId);
        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + actorId));

        cast.getActors().remove(actor);
        return castRepository.save(cast);
    }
}
