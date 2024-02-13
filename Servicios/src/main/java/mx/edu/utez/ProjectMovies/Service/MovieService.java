package mx.edu.utez.ProjectMovies.Service;

import mx.edu.utez.ProjectMovies.Model.Category;
import mx.edu.utez.ProjectMovies.Model.Movie;
import mx.edu.utez.ProjectMovies.Repository.CategoryRepository;
import mx.edu.utez.ProjectMovies.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repo;
    @Autowired
    private CategoryRepository cateRepo;

    public Movie saveMovie(Movie movie) {
        Category category = movie.getCategory();
        category = cateRepo.save(category);
        movie.setCategory(category);
        return repo.save(movie);
    }

    public Page<Movie> PageOfMovie(Pageable page) {
        return repo.findAll(page);
    }

    public void deleteMovie(Long id) {
        repo.deleteById(id);
    }

    public Movie updateMovie(Long id, Movie updateMovie) {
        Movie existingMovie = repo.findById(id).orElse(null);
        if (existingMovie != null) {
            existingMovie.setDirector(updateMovie.getDirector());
            existingMovie.setDuracion(updateMovie.getDuracion());
            existingMovie.setNombre(updateMovie.getNombre());
            existingMovie.setFechaPublicacion(updateMovie.getFechaPublicacion());

            // Obtener la instancia existente de Category
            Category existingCategory = cateRepo.findById(updateMovie.getCategory().getId()).orElse(null);
            if (existingCategory != null) {
                existingMovie.setCategory(existingCategory);
            }

            return repo.save(existingMovie);
        }
        return null;
    }
}
