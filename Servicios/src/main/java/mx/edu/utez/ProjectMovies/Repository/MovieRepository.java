package mx.edu.utez.ProjectMovies.Repository;

import mx.edu.utez.ProjectMovies.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
