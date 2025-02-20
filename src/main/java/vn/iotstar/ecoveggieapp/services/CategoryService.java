package vn.iotstar.ecoveggieapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.ecoveggieapp.models.CategoryModel;
import vn.iotstar.ecoveggieapp.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Lấy tất cả danh mục
    public List<CategoryModel> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    // Lấy danh mục theo tên
    public List<CategoryModel> getCategoriesByName(String categoryName) {
        return categoryRepository.getCategoriesByName(categoryName);
    }

    
}
