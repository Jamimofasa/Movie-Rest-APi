package com.James_Morand.MovieRestAPI.Controller;

import com.James_Morand.MovieRestAPI.Movie.Cast;
import com.James_Morand.MovieRestAPI.Service.CastService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cast")
@Tag(name = "Cast", description = "Manage the cast and their actors. A cast belongs to one movie; a cast can have many actors; an actor can appear in many casts.")
public class CastController {

    @Autowired
    private CastService castService;

    @GetMapping
    @Operation(summary = "Get all cast")
    public ResponseEntity<List<Cast>> getAllCast() {
        return ResponseEntity.ok(castService.getAllCast());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a cast by ID")
    public ResponseEntity<Cast> getCast(
            @Parameter(description = "ID of the cast") @PathVariable int id) {
        return ResponseEntity.ok(castService.getCast(id));
    }

    @PostMapping
    @Operation(summary = "Create a new (empty) cast")
    public ResponseEntity<Cast> addCast(@RequestBody Cast cast) {
        return new ResponseEntity<>(castService.addCast(cast), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Replace the actor list of a cast")
    public ResponseEntity<Cast> updateCast(
            @PathVariable int id,
            @RequestBody Cast cast) {
        return ResponseEntity.ok(castService.updateCast(id, cast));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a cast by ID")
    public ResponseEntity<Void> deleteCast(@PathVariable int id) {
        castService.deleteCast(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{castId}/actors/{actorId}")
    @Operation(
        summary = "Add an actor to a cast",
        description = "Links an existing actor to an existing cast. The actor can already belong to other casts."
    )
    public ResponseEntity<Cast> addActorToCast(
            @Parameter(description = "ID of the cast")  @PathVariable int castId,
            @Parameter(description = "ID of the actor") @PathVariable int actorId) {
        return ResponseEntity.ok(castService.addActorToCast(castId, actorId));
    }

    @DeleteMapping("/{castId}/actors/{actorId}")
    @Operation(
        summary = "Remove an actor from a cast",
        description = "Unlinks the actor from the cast. The actor and cast themselves are not deleted."
    )
    public ResponseEntity<Cast> removeActorFromCast(
            @Parameter(description = "ID of the cast")  @PathVariable int castId,
            @Parameter(description = "ID of the actor") @PathVariable int actorId) {
        return ResponseEntity.ok(castService.removeActorFromCast(castId, actorId));
    }
}
