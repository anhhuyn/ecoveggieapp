package vn.iotstar.ecoveggieapp.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.ecoveggieapp.models.CartItemModel;
import vn.iotstar.ecoveggieapp.models.CartModel;
import vn.iotstar.ecoveggieapp.models.UserModel;
import vn.iotstar.ecoveggieapp.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    // Lấy tất cả CartItem theo user_id
    public List<CartItemModel> getAllCartItemsByUserId(int userId) {
        return cartRepository.findAllCartItemsByUserId(userId);
    }
    public CartModel getCartByUserId(int userId) {
    	return cartRepository.findCartByUserId(userId)
    	.orElseThrow(() -> new RuntimeException("Không tìm thấy giỏ hàng cho userId: " + userId));
    	}
    
    public void createCartForUser(UserModel user) {
        CartModel cart = new CartModel();
        cart.setUser(user);
        cart.setCreatedAt(new Date());
        cartRepository.save(cart);
    }
    
}
