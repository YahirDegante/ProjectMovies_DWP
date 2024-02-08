package mx.edu.utez.ProjectMovies.Controller;

import mx.edu.utez.ProjectMovies.Model.Movie;
import mx.edu.utez.ProjectMovies.Service.MovieService;
import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/movies/")
@CrossOrigin(origins = "")
public class MovieController {
    @Autowired
    private MovieService service;

    @PostMapping
    public Movie saveMovie(@RequestBody(required = true) Movie movie){
        return service.saveMovie(movie);
    }

    @PostMapping("/page")
    Page <Movie> getPage(Pageable pageable){
        return service.PageOfMovie(pageable);
    }



}
