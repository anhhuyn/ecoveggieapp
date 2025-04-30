package vn.iotstar.ecoveggieapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.ecoveggieapp.models.CartItemModel;
import vn.iotstar.ecoveggieapp.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    // Lấy tất cả CartItem theo user_id
    public List<CartItemModel> getAllCartItemsByUserId(int userId) {
        return cartRepository.findAllCartItemsByUserId(userId);
    }
}
