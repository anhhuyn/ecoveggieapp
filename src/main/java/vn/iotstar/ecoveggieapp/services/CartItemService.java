package vn.iotstar.ecoveggieapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.iotstar.ecoveggieapp.models.CartItemModel;
import vn.iotstar.ecoveggieapp.models.CartModel;
import vn.iotstar.ecoveggieapp.models.ProductModel;
import vn.iotstar.ecoveggieapp.repository.CartItemRepository;
import vn.iotstar.ecoveggieapp.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    CartService cartService;

    @Autowired
    ProductRepository productRepository;


    // Lấy tất cả sản phẩm trong giỏ hàng của một user
    public List<CartItemModel> getCartItemsByUserId(int userId) {
        return cartItemRepository.findCartItemsByUserId(userId);
    }
 // Xóa sản phẩm khỏi giỏ hàng
    public void deleteCartItemById(int cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
    
    
    @Transactional
    public void addToCart(int userId, int productId, int quantityToAdd) {
        // Bước 1: Lấy cart hiện tại của user
        CartModel cart = cartService.getCartByUserId(userId);
        if (cart == null) {
            throw new RuntimeException("Cart not found for userId: " + userId);
        }

        int cartId = cart.getId();

     // Bước 2: Kiểm tra sản phẩm đã có trong giỏ chưa
     Optional<CartItemModel> existingCartItemOpt =
         cartItemRepository.findCartItemByCartAndProductId(cartId, productId);

     // Bước 3: Lấy thông tin sản phẩm
     ProductModel product = productRepository.getProductById(productId)
    		 .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id: " + productId));
     if (product == null) {
         throw new RuntimeException("Không tìm thấy sản phẩm với ID: " + productId);
     }

     // Bước 4: Kiểm tra số lượng tồn kho
     int currentStock = product.getInstock_quantity();
     if (currentStock < quantityToAdd) {
         throw new RuntimeException("Không đủ hàng trong kho. Hiện còn: " + currentStock);
     }

     if (existingCartItemOpt.isPresent()) {
         // Nếu sản phẩm đã có trong giỏ: cập nhật số lượng
         CartItemModel cartItem = existingCartItemOpt.get();
         int newQuantity = cartItem.getQuantity() + quantityToAdd;
         cartItemRepository.updateCartItemQuantity(cartId, productId, newQuantity);
     } else {
         // Nếu chưa có: tạo mới cart item
         CartItemModel newCartItem = new CartItemModel();
         newCartItem.setCart(cart);
         newCartItem.setProduct(product);
         newCartItem.setQuantity(quantityToAdd);
         cartItemRepository.save(newCartItem);
     }

     // Bước 5: Cập nhật lại số lượng tồn kho của sản phẩm
     int updatedStock = currentStock - quantityToAdd;
     productRepository.updateProductStockQuantity(productId, updatedStock);

    }
}
