package com.James_Morand.MovieRestAPI.Service;

import com.James_Morand.MovieRestAPI.Movie.Cast;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CastService {

    private static List<Cast> casts = new ArrayList<>();
    private int idCount = casts.size();

    public List<Cast> getCasts()
    {
        return casts;
    }

    public Cast getCast(int id)
    {
        Iterator<Cast> iterator = casts.iterator();

        while (iterator.hasNext())
        {
            Cast cast = iterator.next();

            if (cast.getId() == id)
            {
                return cast;
            }
        }

        return null;
    }

    //add a cast
    public Cast addCast(Cast cast)
    {

        cast.setId(++idCount);


        casts.add(cast);
        return cast;
    }

    //Delete cast
    public Cast deleteCast(int id)
    {
        Iterator<Cast> iterator = casts.iterator();

        while (iterator.hasNext())
        {
            Cast cast = iterator.next();
            if (cast.getId() == id) {
                iterator.remove();
                return cast;
            }
        }

        return null;
    }

    //Update cast
    public Cast updateCast(Cast cast, int id)
    {
        Iterator<Cast> iterator = casts.iterator();

        while (iterator.hasNext())
        {
            Cast casts_ = iterator.next();
            if (casts_.getId() == id) {


                // add Cast to list of actors

                return casts_;
            }

        }

        cast.setId(id);

        return cast;
    }

}
