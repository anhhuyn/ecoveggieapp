package vn.iotstar.ecoveggieapp.repository;

import vn.iotstar.ecoveggieapp.models.CategoryModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryModel, Integer> {

    // Lấy tất cả danh mục
    @Query(value = "SELECT * FROM categories", nativeQuery = true)
    List<CategoryModel> getAllCategories();
    
    // Lấy danh mục theo tên
    @Query(value = "SELECT * FROM categories WHERE category_name LIKE %:categoryName%", nativeQuery = true)
    List<CategoryModel> getCategoriesByName(@Param("categoryName") String categoryName);

    
}
