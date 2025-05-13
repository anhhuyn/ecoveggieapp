package vn.iotstar.ecoveggieapp.repository;

import vn.iotstar.ecoveggieapp.models.ProductModel;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, Integer> {

	@Query(value = "SELECT p.*, pi.product_image FROM products p " +
	        "OUTER APPLY ( " +
	        "   SELECT TOP 1 pi.product_image FROM product_images pi " +
	        "   WHERE pi.product_id = p.product_id " +
	        "   ORDER BY pi.product_image_id ASC " +
	        ") pi", nativeQuery = true)
	List<ProductModel> getAllProducts();


	@Query(value = "SELECT p.*, pi.product_image FROM products p " +
	        "OUTER APPLY ( " +
	        "   SELECT TOP 1 pi.product_image FROM product_images pi " +
	        "   WHERE pi.product_id = p.product_id " +
	        "   ORDER BY pi.product_image_id ASC " +
	        ") pi " +
	        "ORDER BY p.created_at DESC", nativeQuery = true)
	List<ProductModel> getNewestProducts();


	@Query(value = "SELECT p.*, pi.product_image FROM products p " +
	        "OUTER APPLY ( " +
	        "   SELECT TOP 1 pi.product_image FROM product_images pi " +
	        "   WHERE pi.product_id = p.product_id " +
	        "   ORDER BY pi.product_image_id ASC " +
	        ") pi " +
	        "ORDER BY p.price ASC", nativeQuery = true)
	List<ProductModel> getProductsByPriceAsc();


	@Query(value = "SELECT p.*, pi.product_image FROM products p " +
	        "OUTER APPLY ( " +
	        "   SELECT TOP 1 pi.product_image FROM product_images pi " +
	        "   WHERE pi.product_id = p.product_id " +
	        "   ORDER BY pi.product_image_id ASC " +
	        ") pi " +
	        "ORDER BY p.price DESC", nativeQuery = true)
	List<ProductModel> getProductsByPriceDesc();


	@Query(value = "SELECT p.*, pi.product_image FROM products p " +
	        "OUTER APPLY ( " +
	        "   SELECT TOP 1 pi.product_image FROM product_images pi " +
	        "   WHERE pi.product_id = p.product_id " +
	        "   ORDER BY pi.product_image_id ASC " +
	        ") pi " +
	        "WHERE p.price BETWEEN :minPrice AND :maxPrice " +
	        "ORDER BY p.price ASC", nativeQuery = true)
	List<ProductModel> getProductsByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    
	@Query(value = "SELECT p.*, pi.product_image FROM products p " +
	        "OUTER APPLY ( " +
	        "   SELECT TOP 1 pi.product_image FROM product_images pi " +
	        "   WHERE pi.product_id = p.product_id " +
	        "   ORDER BY pi.product_image_id ASC " +
	        ") pi " +
	        "ORDER BY p.sold_quantity DESC", nativeQuery = true)
	List<ProductModel> getProductsBySoldQuantityDesc();

    
	@Query(value = "SELECT p.*, pi.product_image FROM products p " +
	        "OUTER APPLY ( " +
	        "   SELECT TOP 1 pi.product_image FROM product_images pi " +
	        "   WHERE pi.product_id = p.product_id " +
	        "   ORDER BY pi.product_image_id ASC " +
	        ") pi " +
	        "WHERE LOWER(p.product_name) LIKE LOWER(CONCAT('%', :productName, '%'))", nativeQuery = true)
	List<ProductModel> searchProductsByName(@Param("productName") String productName);

    
    // Lọc theo danh mục
    @Query(value = "SELECT p.*, pi.product_image FROM products p " +
            "OUTER APPLY ( " +
            "   SELECT TOP 1 pi.product_image FROM product_images pi " +
            "   WHERE pi.product_id = p.product_id " +
            "   ORDER BY pi.product_image_id ASC " +
            ") pi " +
            "WHERE p.category_id = :categoryId", nativeQuery = true)
    List<ProductModel> getProductsByCategoryId(@Param("categoryId") int categoryId);



    // Lấy thông tin 1 sản phẩm theo id
    @Query(value = "SELECT p.*, pi.product_image FROM products p " +
            "LEFT JOIN product_images pi ON p.product_id = pi.product_id " +
            "WHERE p.product_id = :productId", nativeQuery = true)
    Optional<ProductModel> getProductById(@Param("productId") int productId);



    @Modifying
    @Transactional
    @Query("UPDATE ProductModel p SET p.instock_quantity = :quantity WHERE p.id = :productId")
    void updateProductStockQuantity(@Param("productId") int productId, @Param("quantity") int quantity);
}
