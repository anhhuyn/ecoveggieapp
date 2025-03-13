package vn.iotstar.ecoveggieapp.repository;

import vn.iotstar.ecoveggieapp.models.ProductModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, Integer> {

    @Query(value = "SELECT p.*, pi.product_image FROM products p " +
            "LEFT JOIN product_images pi ON p.product_id = pi.product_id", nativeQuery = true)
    List<ProductModel> getAllProducts();

    @Query(value = "SELECT p.*, pi.product_image FROM products p " +
            "LEFT JOIN product_images pi ON p.product_id = pi.product_id " +
            "ORDER BY p.created_at DESC", nativeQuery = true)
    List<ProductModel> getNewestProducts();

    @Query(value = "SELECT p.*, pi.product_image FROM products p " +
            "LEFT JOIN product_images pi ON p.product_id = pi.product_id " +
            "ORDER BY p.price ASC", nativeQuery = true)
    List<ProductModel> getProductsByPriceAsc();

    @Query(value = "SELECT p.*, pi.product_image FROM products p " +
            "LEFT JOIN product_images pi ON p.product_id = pi.product_id " +
            "ORDER BY p.price DESC", nativeQuery = true)
    List<ProductModel> getProductsByPriceDesc();

    @Query(value = "SELECT p.*, pi.product_image FROM products p " +
            "LEFT JOIN product_images pi ON p.product_id = pi.product_id " +
            "WHERE p.price BETWEEN :minPrice AND :maxPrice " +
            "ORDER BY p.price ASC", nativeQuery = true)
    List<ProductModel> getProductsByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
    
    @Query(value = "SELECT p.*, pi.product_image FROM products p " +
            "LEFT JOIN product_images pi ON p.product_id = pi.product_id " +
            "ORDER BY p.sold_quantity DESC", nativeQuery = true)
    List<ProductModel> getProductsBySoldQuantityDesc(); // Sắp xếp theo số lượng bán giảm dần
    
    @Query(value = "SELECT p.*, pi.product_image FROM products p " +
            "LEFT JOIN product_images pi ON p.product_id = pi.product_id " +
            "WHERE LOWER(p.product_name) LIKE LOWER(CONCAT('%', :productName, '%'))", nativeQuery = true)
    List<ProductModel> searchProductsByName(@Param("productName") String productName);
    
    // Lọc theo danh mục
    @Query(value = "SELECT p.*, pi.product_image FROM products p " +
            "LEFT JOIN product_images pi ON p.product_id = pi.product_id " +
            "WHERE p.category_id = :categoryId", nativeQuery = true)
    List<ProductModel> getProductsByCategoryId(@Param("categoryId") int categoryId);


}
