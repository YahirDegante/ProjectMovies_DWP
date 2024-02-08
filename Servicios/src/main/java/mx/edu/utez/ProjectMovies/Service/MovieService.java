package mx.edu.utez.ProjectMovies.Service;

import mx.edu.utez.ProjectMovies.Model.Movie;
import mx.edu.utez.ProjectMovies.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repo;

    public Movie saveMovie (Movie bean){
        return repo.save(bean);
    }

    public Page<Movie> PageOfMovie(Pageable page){
        return repo.findAll(page);
    }
}
