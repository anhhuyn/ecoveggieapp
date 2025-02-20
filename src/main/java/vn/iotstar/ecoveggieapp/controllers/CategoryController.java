package vn.iotstar.ecoveggieapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.iotstar.ecoveggieapp.models.CategoryModel;
import vn.iotstar.ecoveggieapp.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Lấy tất cả danh mục
    @GetMapping("/all")
    public ResponseEntity<List<CategoryModel>> getAllCategories() {
        List<CategoryModel> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Tìm danh mục theo tên
    @GetMapping("/search")
    public ResponseEntity<List<CategoryModel>> getCategoriesByName(@RequestParam String name) {
        List<CategoryModel> categories = categoryService.getCategoriesByName(name);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
