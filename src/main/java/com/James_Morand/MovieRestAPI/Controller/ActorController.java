package com.James_Morand.MovieRestAPI.Controller;

import com.James_Morand.MovieRestAPI.Movie.Actor;
import com.James_Morand.MovieRestAPI.Service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/actors")
    @Operation(summary = "Get all actors")
    public ResponseEntity<List<Actor>> getAllActor()
    {
        return ResponseEntity.ok(actorService.getAllActors());
    }

    @GetMapping("/actor/{id}")
    @Operation(summary = "Get all actor")
    public ResponseEntity<Actor> getActor(int id)
    {

        Actor actor = actorService.getActor(id);

        if (actor == null)
            throw new RuntimeException(String.format("No Actor %d Found", id));

        return ResponseEntity.ok(actor) ;
    }

    @PostMapping("/actor")
    @Operation(summary = "Create new actor")
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor)
    {
        return ResponseEntity.ok(actorService.addActor(actor));
    }

    @PutMapping("/actor/{id}")
    @Operation(summary = "Replace or update actor")
    public ResponseEntity<Actor> updateActor(@RequestBody Actor actor, @PathVariable int id)
    {
        return ResponseEntity.ok(actorService.updateActor(id, actor)) ;
    }

    @DeleteMapping("/actor/{id}")
    @Operation(summary = "Delete actor")
    public ResponseEntity<Void> deleteActor(@PathVariable int id)
    {
        actorService.deleteActor(id);
        return ResponseEntity.noContent().build();

    }
}
