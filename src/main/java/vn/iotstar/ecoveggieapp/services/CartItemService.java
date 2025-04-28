package vn.iotstar.ecoveggieapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.ecoveggieapp.models.CartItemModel;
import vn.iotstar.ecoveggieapp.repository.CartItemRepository;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    // Lấy tất cả sản phẩm trong giỏ hàng của một user
    public List<CartItemModel> getCartItemsByUserId(int userId) {
        return cartItemRepository.findCartItemsByUserId(userId);
    }

}
