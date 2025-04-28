package vn.iotstar.ecoveggieapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.ecoveggieapp.models.CartModel;
import vn.iotstar.ecoveggieapp.repository.CartRepository;
@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    // Lấy giỏ hàng của một user
    public CartModel getCartByUserId(int userId) {
        return cartRepository.findCartByUserId(userId);
    }
}
