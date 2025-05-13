package vn.iotstar.ecoveggieapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.iotstar.ecoveggieapp.models.CartItemModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemModel, Integer> {

    // Lấy danh sách sản phẩm trong giỏ hàng của user
    @Query("SELECT ci FROM CartItemModel ci WHERE ci.cart.user.id = :userId")
    List<CartItemModel> findCartItemsByUserId(@Param("userId") int userId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM CartItemModel ci WHERE ci.cart.id = :cartId AND ci.product.id = :productId")
	void deleteCartItemByCartAndProductId(@Param("cartId") int cartId, @Param("productId") int productId);

	
	
	
	// Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
    @Query("SELECT ci FROM CartItemModel ci WHERE ci.cart.id = :cartId AND ci.product.id = :productId")
    Optional<CartItemModel> findCartItemByCartAndProductId(@Param("cartId") int cartId, @Param("productId") int productId);

    // Cập nhật số lượng giỏ hàng
    @Modifying
    @Transactional
    @Query("UPDATE CartItemModel ci SET ci.quantity = :quantity WHERE ci.cart.id = :cartId AND ci.product.id = :productId")
    void updateCartItemQuantity(@Param("cartId") int cartId, @Param("productId") int productId, @Param("quantity") int quantity);

   
}
