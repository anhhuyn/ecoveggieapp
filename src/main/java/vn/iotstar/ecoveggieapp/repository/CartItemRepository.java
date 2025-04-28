package vn.iotstar.ecoveggieapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.iotstar.ecoveggieapp.models.CartItemModel;

import java.util.List;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemModel, Integer> {

    // Lấy danh sách sản phẩm trong giỏ hàng của user
    @Query("SELECT ci FROM CartItemModel ci WHERE ci.cart.user.id = :userId")
    List<CartItemModel> findCartItemsByUserId(@Param("userId") int userId);
}
