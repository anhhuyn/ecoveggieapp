package vn.iotstar.ecoveggieapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.iotstar.ecoveggieapp.models.CartModel;

@Repository
public interface CartRepository extends CrudRepository<CartModel, Integer> {

    // Mỗi user chỉ có 1 cart
    @Query("SELECT c FROM CartModel c WHERE c.user.id = :userId")
    CartModel findCartByUserId(@Param("userId") int userId);
}
