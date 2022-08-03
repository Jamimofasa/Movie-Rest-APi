package com.James_Morand.MovieRestAPI.Service;

import com.James_Morand.MovieRestAPI.Movie.Actor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Component
public class ActorService {

    public Random random;
    public static List<Actor> actors = new ArrayList<>();
    private int idCount = actors.size();




//    static {
//
//        moviesList.add(new Movie(1,"Avengers: Age of Ultron",141 ,"5/2/25" , 495.2));
//        moviesList.add(new Movie(2,"Star Wars: Episode IV - A New Hope",121 ,"May 25, 1977" , 11));
//        moviesList.add(new Movie(3,"Blazing Saddles",93 ,"February 7, 1974" , 119.6));
//
//    }

    //Get all Actor
    public List<Actor> getAllActor()
    {
        return actors;
    }

    //Get one Actor
    public Actor getActor(int id)
    {
        Iterator<Actor> iterator = actors.iterator();

        while (iterator.hasNext())
        {
            Actor Actor = iterator.next();
            if (Actor.getId() == id)
                return Actor;
        }

        return null;
    }

    //add a Actor
    public Actor addActor(Actor actor)
    {

        actor.setId(++idCount);


        actors.add(actor);
        return actor;
    }

    //Delete Actor
    public Actor deleteActor(int id)
    {
        Iterator<Actor> iterator = actors.iterator();

        while (iterator.hasNext())
        {
            Actor actor = iterator.next();
            if (actor.getId() == id) {
                iterator.remove();
                return actor;
            }
        }

        return null;
    }

    //Update Actor
    public Actor updateActor(Actor actor, int id)
    {
        Iterator<Actor> iterator = actors.iterator();

        while (iterator.hasNext())
        {
            Actor actors_ = iterator.next();
            if (actors_.getId() == id) {

                actors_.setAge(actor.getAge());
                actors_.setName(actor.getName());
                // add actor to list of actors

                return actors_;
            }

        }

        actor.setId(id);

        return actor;
    }

}
