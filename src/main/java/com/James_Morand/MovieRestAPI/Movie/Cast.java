package com.James_Morand.MovieRestAPI.Movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//Cast-to-Actor
//many-to-many

//cast-to-movie
//one-to-one

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "MOVIE_CAST")
public class Cast {

    @Id
    @GeneratedValue
    private int id;

//    @OneToOne
//    private Movie movie;
//
//    @ManyToMany
//    private List<Actor> actors;





}
