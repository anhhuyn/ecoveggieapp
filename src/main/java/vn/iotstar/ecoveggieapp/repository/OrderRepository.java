package vn.iotstar.ecoveggieapp.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import vn.iotstar.ecoveggieapp.models.OrderModel;

@Repository
public interface OrderRepository extends CrudRepository<OrderModel, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO orders (customer_id, total_amount, payment_method, status, note, address_id, created_at) " +
            "VALUES (:customerId, :totalAmount, :paymentMethod, :status, :note, :addressId, GETDATE())", nativeQuery = true)
    void insertOrder(
            @Param("customerId") int customerId,
            @Param("totalAmount") double totalAmount,
            @Param("paymentMethod") String paymentMethod,
            @Param("status") String status,
            @Param("note") String note,
            @Param("addressId") int addressId
    );

    // Truy vấn lấy đơn hàng mới nhất của khách hàng
    @Query(value = "SELECT TOP 1 * FROM orders WHERE customer_id = :customerId ORDER BY created_at DESC", nativeQuery = true)
    OrderModel findLatestOrderByCustomerId(@Param("customerId") int customerId);
}
