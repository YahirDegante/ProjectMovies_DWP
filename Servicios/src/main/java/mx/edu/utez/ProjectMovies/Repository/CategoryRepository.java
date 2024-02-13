package mx.edu.utez.ProjectMovies.Repository;

import mx.edu.utez.ProjectMovies.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
