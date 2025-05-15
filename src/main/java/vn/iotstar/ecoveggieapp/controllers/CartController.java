package vn.iotstar.ecoveggieapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.iotstar.ecoveggieapp.models.CartItemModel;
import vn.iotstar.ecoveggieapp.services.CartItemService;
import vn.iotstar.ecoveggieapp.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartItemService cartService;

    @Autowired
    private CartService cartService1;


    // API lấy tất cả sản phẩm trong giỏ hàng của 1 user (qua query param)
    @GetMapping("/user")
    public ResponseEntity<List<CartItemModel>> getCartItems(@RequestParam("user_id") int userId) {
        List<CartItemModel> cartItems = cartService.getCartItemsByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }
    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable("cartItemId") Integer id) {
        cartService.deleteCartItemById(id);
        return ResponseEntity.ok("Xóa thành công");
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            @RequestParam("user_id") int userId,
            @RequestParam("product_id") int productId,
            @RequestParam("quantity") int quantity
    ) {
        try {
            cartService.addToCart(userId, productId, quantity);
            return ResponseEntity.ok("Đã thêm sản phẩm vào giỏ hàng");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
        }
    }
    

    @GetMapping("/count")
    public ResponseEntity<Integer> countCartItems(@RequestParam("user_id") int userId) {
        int count = cartService1.countCartItemsByUserId(userId);
        return ResponseEntity.ok(count);
    }

}

