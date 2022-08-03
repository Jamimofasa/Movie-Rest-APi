package com.James_Morand.MovieRestAPI.Contoller;

import com.James_Morand.MovieRestAPI.Movie.Cast;
import com.James_Morand.MovieRestAPI.Service.CastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CastController {

    @Autowired
    private CastService castService;

    @GetMapping("/casts")
    public List<Cast> getCasts()
    {
        return castService.getCasts();
    }

    @GetMapping("/cast/{id}")
    public Cast getCast(int id)
    {

        Cast cast = castService.getCast(id);

        if (cast == null)
            throw new RuntimeException(String.format("No Cast %d Found", id));

        return cast;
    }

    @PostMapping("/cast")
    public Cast addCast(@RequestBody Cast cast)
    {
       return castService.addCast(cast);
    }

    @PutMapping("/cast/{id}")
    public Cast updateCast(@RequestBody Cast cast, @PathVariable int id)
    {
        return castService.updateCast(cast,id);
    }

    @DeleteMapping("/cast/{id}")
    public void deleteCast(@PathVariable int id)
    {
        if (castService.deleteCast(id) == null)
            throw new RuntimeException(String.format("No Cast %d Found", id));
    }
}
