package com.James_Morand.MovieRestAPI.Service;

import com.James_Morand.MovieRestAPI.Exception.DuplicateResourceException;
import com.James_Morand.MovieRestAPI.Exception.ResourceNotFoundException;
import com.James_Morand.MovieRestAPI.Movie.Actor;
import com.James_Morand.MovieRestAPI.Repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Actor getActor(int id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id));
    }

    public Actor addActor(Actor actor) {
        if (actorRepository.existsByNameIgnoreCase(actor.getName())) {
            throw new DuplicateResourceException(
                "An actor named '" + actor.getName() + "' already exists");
        }
        actor.setId(0);
        return actorRepository.save(actor);
    }

    public Actor updateActor(int id, Actor actor) {
        Actor existing = getActor(id);
        existing.setName(actor.getName());
        existing.setAge(actor.getAge());
        return actorRepository.save(existing);
    }

    public void deleteActor(int id) {
        Actor actor = getActor(id);
        actorRepository.delete(actor);
    }

    public List<Actor> searchByName(String name) {
        return actorRepository.findByNameContainingIgnoreCase(name);
    }
}
