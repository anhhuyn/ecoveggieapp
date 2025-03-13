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
    
    
    // Lấy danh mục kèm số lượng sản phẩm
    @Query(value = """
    	    SELECT c.category_id, c.category_name, c.thumbnail, COUNT(p.product_id) AS productCount 
    	    FROM categories c 
    	    LEFT JOIN products p ON c.category_id = p.category_id 
    	    GROUP BY c.category_id, c.category_name, c.thumbnail
    	    """, nativeQuery = true)
    	List<Object[]> getAllCategoriesWithProductCount();
    
    
    // Lấy danh mục theo tên
    	 @Query(value = """
    		        SELECT c.category_id, c.category_name, c.thumbnail, COALESCE(COUNT(p.product_id), 0) AS productCount 
    		        FROM categories c 
    		        LEFT JOIN products p ON c.category_id = p.category_id 
    		        WHERE c.category_name LIKE %:categoryName% 
    		        GROUP BY c.category_id, c.category_name, c.thumbnail
    		        """, nativeQuery = true)
    		    List<Object[]> getCategoriesByNameWithProductCount(@Param("categoryName") String categoryName);
    
    // Đếm số lượng sản phẩm trong mỗi danh mục
    @Query(value = "SELECT COUNT(*) FROM products WHERE category_id = :categoryId", nativeQuery = true)
    int countProductsByCategory(@Param("categoryId") int categoryId);


    
}
