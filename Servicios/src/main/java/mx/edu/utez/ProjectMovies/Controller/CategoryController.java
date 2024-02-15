package mx.edu.utez.ProjectMovies.Controller;

import mx.edu.utez.ProjectMovies.Model.Category;
import mx.edu.utez.ProjectMovies.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category/")
@CrossOrigin(origins = "http://localhost:5174")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping
    public Category saveCategory(@RequestBody(required = true) Category category){
        return service.saveCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return service.getAllCategories();
    }

}
