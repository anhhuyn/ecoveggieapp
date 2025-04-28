package vn.iotstar.ecoveggieapp.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import vn.iotstar.ecoveggieapp.models.OrderDetailModel;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetailModel, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO order_details (order_id, product_id, quantity, price) " +
            "VALUES (:orderId, :productId, :quantity, :price)", nativeQuery = true)
    void insertOrderDetail(
            @Param("orderId") int orderId,
            @Param("productId") int productId,
            @Param("quantity") int quantity,
            @Param("price") double price 
    );
}
