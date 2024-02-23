package mx.edu.utez.ProjectMovies.Controller;

import mx.edu.utez.ProjectMovies.Model.Movie;
import mx.edu.utez.ProjectMovies.Service.MovieService;
import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/movies/")
@CrossOrigin(origins = "http://localhost:5173")
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

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        service.deleteMovie(id);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        return service.updateMovie(id, movie);
    }
}
