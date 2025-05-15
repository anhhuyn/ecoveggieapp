package vn.iotstar.ecoveggieapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import vn.iotstar.ecoveggieapp.models.OrderDetailModel;
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
    
    // Chờ xác nhận
    @Query(value = """
    	    SELECT o.id AS order_id,
    	           o.total_amount,
    	           o.payment_method,
    	           o.note,
    	           o.created_at,
    	           o.status,
    	           
    	           p.product_name,
    	           p.unit,
    	           od.price,
    	           od.quantity AS first_product_quantity,

    	           -- Ảnh đầu tiên của sản phẩm
    	           (
    	               SELECT TOP 1 pi.product_image
    	               FROM product_images pi
    	               WHERE pi.product_id = p.product_id
    	               ORDER BY pi.product_image_id ASC
    	           ) AS product_image,

    	           -- Tổng số lượng sản phẩm trong đơn
    	           (
    	               SELECT SUM(od2.quantity)
    	               FROM order_details od2
    	               WHERE od2.order_id = o.id
    	           ) AS total_quantity_in_order,

    	           -- Tổng số dòng order_details (số loại sản phẩm)
    	           (
    	               SELECT COUNT(*)
    	               FROM order_details od3
    	               WHERE od3.order_id = o.id
    	           ) AS total_items_in_order

    	    FROM orders o
    	    JOIN order_details od ON o.id = od.order_id
    	    JOIN products p ON od.product_id = p.product_id

    	    WHERE o.customer_id = :customerId AND o.status = 'Pending Confirm'
    	    AND od.id = (
    	        SELECT TOP 1 od2.id
    	        FROM order_details od2
    	        WHERE od2.order_id = o.id
    	        ORDER BY od2.id ASC
    	    )
    	    ORDER BY o.created_at DESC
    	    """, nativeQuery = true)
    	List<Object[]> findPendingOrdersWithFirstProductRaw(@Param("customerId") int customerId);
    	
    	 // Chờ lấy hàng
        @Query(value = """
        	    SELECT o.id AS order_id,
        	           o.total_amount,
        	           o.payment_method,
        	           o.note,
        	           o.created_at,
        	           o.status,
        	           
        	           p.product_name,
        	           p.unit,
        	           od.price,
        	           od.quantity AS first_product_quantity,

        	           -- Ảnh đầu tiên của sản phẩm
        	           (
        	               SELECT TOP 1 pi.product_image
        	               FROM product_images pi
        	               WHERE pi.product_id = p.product_id
        	               ORDER BY pi.product_image_id ASC
        	           ) AS product_image,

        	           -- Tổng số lượng sản phẩm trong đơn
        	           (
        	               SELECT SUM(od2.quantity)
        	               FROM order_details od2
        	               WHERE od2.order_id = o.id
        	           ) AS total_quantity_in_order,

        	           -- Tổng số dòng order_details (số loại sản phẩm)
        	           (
        	               SELECT COUNT(*)
        	               FROM order_details od3
        	               WHERE od3.order_id = o.id
        	           ) AS total_items_in_order

        	    FROM orders o
        	    JOIN order_details od ON o.id = od.order_id
        	    JOIN products p ON od.product_id = p.product_id

        	    WHERE o.customer_id = :customerId AND o.status = 'Pending Delivery'
        	    AND od.id = (
        	        SELECT TOP 1 od2.id
        	        FROM order_details od2
        	        WHERE od2.order_id = o.id
        	        ORDER BY od2.id ASC
        	    )
        	    ORDER BY o.created_at DESC
        	    """, nativeQuery = true)
        	List<Object[]> findWaitingDeliveryOrdersWithFirstProductRaw(@Param("customerId") int customerId);
        	
        	 // Truy vấn đơn hàng "Chờ giao hàng" (Pending Ship)
            @Query(value = """
                    SELECT o.id AS order_id,
                           o.total_amount,
                           o.payment_method,
                           o.note,
                           o.created_at,
                           o.status,
                           p.product_name,
                           p.unit,
                           od.price,
                           od.quantity AS first_product_quantity,

                           -- Ảnh đầu tiên của sản phẩm
                           (
                               SELECT TOP 1 pi.product_image
                               FROM product_images pi
                               WHERE pi.product_id = p.product_id
                               ORDER BY pi.product_image_id ASC
                           ) AS product_image,

                           -- Tổng số lượng sản phẩm trong đơn
                           (
                               SELECT SUM(od2.quantity)
                               FROM order_details od2
                               WHERE od2.order_id = o.id
                           ) AS total_quantity_in_order,

                           -- Tổng số dòng order_details (số loại sản phẩm)
                           (
                               SELECT COUNT(*)
                               FROM order_details od3
                               WHERE od3.order_id = o.id
                           ) AS total_items_in_order

                    FROM orders o
                    JOIN order_details od ON o.id = od.order_id
                    JOIN products p ON od.product_id = p.product_id

                    WHERE o.customer_id = :customerId AND o.status = 'Pending Ship'
                    AND od.id = (
                        SELECT TOP 1 od2.id
                        FROM order_details od2
                        WHERE od2.order_id = o.id
                        ORDER BY od2.id ASC
                    )
                    ORDER BY o.created_at DESC
            """, nativeQuery = true)
            List<Object[]> findPendingShipOrdersWithFirstProductRaw(@Param("customerId") int customerId);

            // Truy vấn đơn hàng "Đã giao"
            @Query(value = """
                    SELECT o.id AS order_id,
                           o.total_amount,
                           o.payment_method,
                           o.note,
                           o.created_at,
                           o.status,
                           p.product_name,
                           p.unit,
                           od.price,
                           od.quantity AS first_product_quantity,

                           -- Ảnh đầu tiên của sản phẩm
                           (
                               SELECT TOP 1 pi.product_image
                               FROM product_images pi
                               WHERE pi.product_id = p.product_id
                               ORDER BY pi.product_image_id ASC
                           ) AS product_image,

                           -- Tổng số lượng sản phẩm trong đơn
                           (
                               SELECT SUM(od2.quantity)
                               FROM order_details od2
                               WHERE od2.order_id = o.id
                           ) AS total_quantity_in_order,

                           -- Tổng số dòng order_details (số loại sản phẩm)
                           (
                               SELECT COUNT(*)
                               FROM order_details od3
                               WHERE od3.order_id = o.id
                           ) AS total_items_in_order

                    FROM orders o
                    JOIN order_details od ON o.id = od.order_id
                    JOIN products p ON od.product_id = p.product_id

                    WHERE o.customer_id = :customerId AND o.status = 'Delivered'
                    AND od.id = (
                        SELECT TOP 1 od2.id
                        FROM order_details od2
                        WHERE od2.order_id = o.id
                        ORDER BY od2.id ASC
                    )
                    ORDER BY o.created_at DESC
            """, nativeQuery = true)
            List<Object[]> findDeliveredOrdersWithFirstProductRaw(@Param("customerId") int customerId);

            // Truy vấn đơn hàng "Đã hủy"
            @Query(value = """
                    SELECT o.id AS order_id,
                           o.total_amount,
                           o.payment_method,
                           o.note,
                           o.created_at,
                           o.status,
                           p.product_name,
                           p.unit,
                           od.price,
                           od.quantity AS first_product_quantity,

                           -- Ảnh đầu tiên của sản phẩm
                           (
                               SELECT TOP 1 pi.product_image
                               FROM product_images pi
                               WHERE pi.product_id = p.product_id
                               ORDER BY pi.product_image_id ASC
                           ) AS product_image,

                           -- Tổng số lượng sản phẩm trong đơn
                           (
                               SELECT SUM(od2.quantity)
                               FROM order_details od2
                               WHERE od2.order_id = o.id
                           ) AS total_quantity_in_order,

                           -- Tổng số dòng order_details (số loại sản phẩm)
                           (
                               SELECT COUNT(*)
                               FROM order_details od3
                               WHERE od3.order_id = o.id
                           ) AS total_items_in_order

                    FROM orders o
                    JOIN order_details od ON o.id = od.order_id
                    JOIN products p ON od.product_id = p.product_id

                    WHERE o.customer_id = :customerId AND o.status = 'Cancel'
                    AND od.id = (
                        SELECT TOP 1 od2.id
                        FROM order_details od2
                        WHERE od2.order_id = o.id
                        ORDER BY od2.id ASC
                    )
                    ORDER BY o.created_at DESC
            """, nativeQuery = true)
            List<Object[]> findCanceledOrdersWithFirstProductRaw(@Param("customerId") int customerId);
            
            // Đếm số lượng đơn hàng theo trạng thái truyền vào
            @Query(value = "SELECT COUNT(*) FROM orders WHERE customer_id = :customerId AND LTRIM(RTRIM(status)) COLLATE Latin1_General_CS_AS = :status", nativeQuery = true)
            int countOrdersByStatus(@Param("customerId") int customerId, @Param("status") String status);

}
