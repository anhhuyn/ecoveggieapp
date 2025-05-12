package vn.iotstar.ecoveggieapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.iotstar.ecoveggieapp.models.CartItemModel;
import vn.iotstar.ecoveggieapp.models.CartModel;

@Repository
public interface CartRepository extends CrudRepository<CartModel, Integer> {

    // Lấy tất cả các CartItem theo user_id
    @Query(value = "SELECT * FROM cart_item ci WHERE ci.cart_id IN (SELECT c.id FROM cart c WHERE c.user_id = :userId)", nativeQuery = true)
    List<CartItemModel> findAllCartItemsByUserId(int userId);
}
