package com.James_Morand.MovieRestAPI.Movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//Actor-to-Cast
//many-to-many

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "ACTOR")
public class Actor {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AGE",nullable = false)
    private int age;

    @ManyToMany
    private List<Cast> cast;
}
