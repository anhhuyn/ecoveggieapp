package vn.iotstar.ecoveggieapp.repository;

import vn.iotstar.ecoveggieapp.models.ProductModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, Integer> {

    // Lấy tất cả sản phẩm
    @Query(value = "SELECT * FROM products", nativeQuery = true)
    List<ProductModel> getAllProducts();

    // Lấy sản phẩm theo thứ tự mới nhất (sắp xếp theo created_at giảm dần)
    @Query(value = "SELECT * FROM products ORDER BY created_at DESC", nativeQuery = true)
    List<ProductModel> getNewestProducts();

    // Lấy sản phẩm theo giá tăng dần
    @Query(value = "SELECT * FROM products ORDER BY price ASC", nativeQuery = true)
    List<ProductModel> getProductsByPriceAsc();

    // Lấy sản phẩm theo giá giảm dần
    @Query(value = "SELECT * FROM products ORDER BY price DESC", nativeQuery = true)
    List<ProductModel> getProductsByPriceDesc();

    // Lấy sản phẩm theo khoảng giá
    @Query(value = "SELECT * FROM products WHERE price BETWEEN :minPrice AND :maxPrice ORDER BY price ASC", nativeQuery = true)
    List<ProductModel> getProductsByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}
