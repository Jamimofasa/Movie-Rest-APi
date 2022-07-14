package com.James_Morand.MovieRestAPI.Movie;

public class Movie {


    //This movie class will contain a:
    // Movie id
    // Movie name
    // Movie duration in minutes
    // Date movie wa released
    // How much did it cost to make
    private int id;
    private String name;
    private int movieLength;
    private String releaseDate;
    private double costToMake;

    public Movie(int id, String name, int duration, String releaseDate, double costToMake) {
        this.id = id;
        this.name = name;
        this.movieLength = duration;
        this.releaseDate = releaseDate;
        this.costToMake = costToMake;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(int movieLength) {
        this.movieLength = movieLength;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getCostToMake() {
        return costToMake;
    }

    public void setCostToMake(double costToMake) {
        this.costToMake = costToMake;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + movieLength +
                ", studio='" + releaseDate + '\'' +
                ", costToMake='" + costToMake + '\'' +
                '}';
    }
}
