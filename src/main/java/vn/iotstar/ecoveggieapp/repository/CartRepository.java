package vn.iotstar.ecoveggieapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.iotstar.ecoveggieapp.models.CartItemModel;
import vn.iotstar.ecoveggieapp.models.CartModel;

@Repository
public interface CartRepository extends CrudRepository<CartModel, Integer> {

    // Lấy tất cả các CartItem theo user_id
    @Query(value = "SELECT * FROM cart_item ci WHERE ci.cart_id IN (SELECT c.id FROM cart c WHERE c.user_id = :userId)", nativeQuery = true)
    List<CartItemModel> findAllCartItemsByUserId(int userId);
    @Query("SELECT c FROM CartModel c WHERE c.user.id = :userId")
    Optional<CartModel> findCartByUserId(@Param("userId") int userId);
    
    @Query(value = "SELECT COUNT(*) FROM cart_item ci WHERE ci.cart_id IN (SELECT c.id FROM cart c WHERE c.user_id = :userId)", nativeQuery = true)
    int countCartItemsByUserId(@Param("userId") int userId);

}
