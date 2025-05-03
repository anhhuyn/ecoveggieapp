package vn.iotstar.ecoveggieapp.repository;

import vn.iotstar.ecoveggieapp.models.ProductModel;
import vn.iotstar.ecoveggieapp.models.ReviewModel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<ReviewModel, Integer> {

    @Query(value = "SELECT DISTINCT p.* FROM orders o " +
            "JOIN order_details od ON o.id = od.order_id " +
            "JOIN products p ON od.product_id = p.product_id " +
            "LEFT JOIN review r ON r.product_id = p.product_id AND r.user_id = :userId " +
            "WHERE o.customer_id = :userId AND r.review_id IS NULL", nativeQuery = true)
    List<ProductModel> getUnreviewedProductsByUser(@Param("userId") int userId);
    
    @Query("SELECT r FROM ReviewModel r JOIN FETCH r.user LEFT JOIN FETCH r.mediaList WHERE r.product.product_id = :productId")
    	List<ReviewModel> findReviewsByProductIdWithUserAndMedia(@Param("productId") int productId);


}
