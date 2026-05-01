package com.James_Morand.MovieRestAPI.Movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACTOR")
@Schema(description = "Represents an actor in the system")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated actor ID", example = "1")
    private int id;

    @NotNull(message = "Name cannot be blank")
    @Size(max = 100, message = "Name must be 100 characters or fewer")
    @Column(name = "NAME", nullable = false)
    @Schema(description = "Full name of the actor", example = "Robert Downey Jr.")
    private String name;

    @Positive(message = "Age must be a positive number")
    @Column(name = "AGE", nullable = false)
    @Schema(description = "Age of the actor", example = "58")
    private int age;

    /**
     * Inverse side of the Cast-Actor many-to-many.
     * Cast owns the join table (CAST_ACTOR); Actor is the non-owning side.
     * An actor can belong to many casts (e.g. sequels, different productions).
     */
    @ManyToMany(mappedBy = "actors")
    @JsonIgnoreProperties("actors")
    @Schema(description = "All casts this actor has been part of")
    private List<Cast> casts = new ArrayList<>();
}
