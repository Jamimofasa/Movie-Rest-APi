package com.James_Morand.MovieRestAPI.Movie;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

//movie-to-cast
//one-to-one


@Data // Lombok takes care of getters and setters
@AllArgsConstructor // Lombok takes care of all argument constructor
@NoArgsConstructor // Lombok takes care of default constructor
@ToString //@Overrides ToString method
@Entity
@Table(name = "MOVIE")
public class Movie {

    //This movie class will contain a:
    // Movie id
    // Movie name
    // Movie duration in minutes
    // Date movie wa released
    // How much did it cost to make
    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "Can't be left blank")
    @Size(max = 30)
    @Column(name = "NAME",nullable = false)
    private String name;

    @Positive(message = "needs to be a positive number")
    @Column(name = "MOVIE_LENGTH",nullable = false)
    private int movieLength;

    @Column(name = "RELEASE_DATE",nullable = false)
    private String releaseDate;

    @Column(name = "COST_TO_MAKE",nullable = false)
    private double costToMake;

    @OneToOne
    private Cast cast;

    //Constructors and all getters and setters are taken care of with Lombok @Data @NoArgsConstructor @AllArgsConstructor
//
//    public Movie(int id, String name, int duration, String releaseDate, double costToMake) {
//        this.id = id;
//        this.name = name;
//        this.movieLength = duration;
//        this.releaseDate = releaseDate;
//        this.costToMake = costToMake;
//    }
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getMovieLength() {
//        return movieLength;
//    }
//
//    public void setMovieLength(int movieLength) {
//        this.movieLength = movieLength;
//    }
//
//    public String getReleaseDate() {
//        return releaseDate;
//    }
//
//    public void setReleaseDate(String releaseDate) {
//        this.releaseDate = releaseDate;
//    }
//
//    public double getCostToMake() {
//        return costToMake;
//    }
//
//    public void setCostToMake(double costToMake) {
//        this.costToMake = costToMake;
//    }
//
//    @Override
//    public String toString() {
//        return "Movie{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", duration=" + movieLength +
//                ", studio='" + releaseDate + '\'' +
//                ", costToMake='" + costToMake + '\'' +
//                '}';
//    }
}
