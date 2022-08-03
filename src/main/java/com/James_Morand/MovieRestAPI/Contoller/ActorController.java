package com.James_Morand.MovieRestAPI.Contoller;

import com.James_Morand.MovieRestAPI.Movie.Actor;
import com.James_Morand.MovieRestAPI.Movie.Cast;
import com.James_Morand.MovieRestAPI.Service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/actors")
    public List<Actor> getCActor()
    {
        return actorService.getAllActor();
    }

    @GetMapping("/actor/{id}")
    public Actor getActor(int id)
    {

        Actor actor = actorService.getActor(id);

        if (actor == null)
            throw new RuntimeException(String.format("No Actor %d Found", id));

        return actor;
    }

    @PostMapping("/actor")
    public Actor addActor(@RequestBody Actor actor)
    {
        return actorService.addActor(actor);
    }

    @PutMapping("/actor/{id}")
    public Actor updateActor(@RequestBody Actor actor, @PathVariable int id)
    {
        return actorService.updateActor(actor,id);
    }

    @DeleteMapping("/actor/{id}")
    public void deleteActor(@PathVariable int id)
    {
        if (actorService.deleteActor(id) == null)
            throw new RuntimeException(String.format("No Actor %d Found", id));
    }
}
