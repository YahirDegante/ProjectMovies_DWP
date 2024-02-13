package mx.edu.utez.ProjectMovies.Service;

import mx.edu.utez.ProjectMovies.Model.Category;
import mx.edu.utez.ProjectMovies.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    public Category saveCategory(Category category) {
        return repo.save(category);
    }

    public List<Category> getAllCategories() {
        return repo.findAll();
    }

}
