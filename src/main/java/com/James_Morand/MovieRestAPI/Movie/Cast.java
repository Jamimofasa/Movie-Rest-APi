package com.James_Morand.MovieRestAPI.Movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A Cast belongs to one Movie.
 * A Cast has many Actors.
 * An Actor can belong to many Casts.
 *
 * Relationship summary:
 *   Movie  ──(OneToOne)──►  Cast
 *   Cast   ──(ManyToMany)──► Actor   [CAST_ACTOR join table, owned by Cast]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MOVIE_CAST")
@Schema(description = "The cast of a movie — a collection of actors")
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated cast ID", example = "1")
    private int id;

    /**
     * Cast owns the join table so actors can be added/removed via Cast directly.
     * Actor has the inverse (mappedBy = "casts") side.
     */
    @ManyToMany
    @JoinTable(
        name = "CAST_ACTOR",
        joinColumns        = @JoinColumn(name = "cast_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @JsonIgnoreProperties("casts")
    @Schema(description = "Actors who are part of this cast")
    private List<Actor> actors = new ArrayList<>();

    /**
     * Back-reference to the Movie that owns this Cast.
     * Movie is the owning side (has the FK column via @JoinColumn).
     */
    @OneToOne(mappedBy = "cast")
    @JsonIgnoreProperties("cast")
    @Schema(description = "The movie this cast belongs to")
    private Movie movie;
}
