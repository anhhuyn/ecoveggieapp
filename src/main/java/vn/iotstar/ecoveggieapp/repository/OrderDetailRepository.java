package vn.iotstar.ecoveggieapp.repository;

import java.util.List;

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
    
    @Query(value = """
			SELECT
			    o.id AS order_id,
			    o.customer_id,
			    o.total_amount,
			    o.payment_method,
			    o.status,
			    o.note,
			    o.created_at,

			    -- Địa chỉ giao hàng sửa lại đúng tên cột
			    a.detail AS address_line,
			    a.wards AS ward,
			    a.district,
			    a.province,
			    a.phone AS receiver_phone,

			    -- Thông tin từng sản phẩm
			    p.product_name,
			    p.unit,
			    od.price,
			    od.quantity,
			    p.product_id,  -- Added product_id
			    (
			        SELECT TOP 1 pi.product_image
			        FROM product_images pi
			        WHERE pi.product_id = p.product_id
			        ORDER BY pi.product_image_id ASC
			    ) AS product_image

			FROM order_details od
			JOIN orders o ON od.order_id = o.id
			JOIN products p ON od.product_id = p.product_id
			JOIN address a ON o.address_id = a.id

			WHERE od.order_id = :orderId
			""", nativeQuery = true)
	List<Object[]> findFullOrderDetailByOrderId(@Param("orderId") int orderId);
}
