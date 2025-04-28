package vn.iotstar.ecoveggieapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.iotstar.ecoveggieapp.models.CartItemModel;
import vn.iotstar.ecoveggieapp.services.CartItemService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartItemService cartItemService;

    // API lấy tất cả sản phẩm trong giỏ hàng của 1 user
    @GetMapping("/items/{userId}")
    public ResponseEntity<List<CartItemModel>> getCartItemsByUser(@PathVariable("userId") int userId) {
        List<CartItemModel> cartItems = cartItemService.getCartItemsByUserId(userId);

        if (cartItems == null || cartItems.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content nếu không có sản phẩm
        }

        return ResponseEntity.ok(cartItems); // 200 OK với danh sách sản phẩm
    }
}
