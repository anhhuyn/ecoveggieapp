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
   /* @Transactional
    public void addToCart(int userId, int productId, int quantityToAdd) {
        // Bước 1: Lấy cart hiện tại của user
        CartModel cart = cartService.getCartByUserId(userId);
        if (cart == null) {
            throw new RuntimeException("Cart not found for userId: " + userId);
        }

        int cartId = cart.getId();

        // Bước 2: Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        Optional<CartItemModel> existingCartItemOpt = cartItemRepository.findCartItemByCartAndProductId(cartId, productId);

        // Bước 3: Lấy thông tin sản phẩm từ database
        ProductModel product = productRepository.findProductById(productId);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + productId);
        }

        int currentStock = product.getInstock_quantity();
        if (currentStock < quantityToAdd) {
            throw new RuntimeException("Not enough stock. Current: " + currentStock + ", Requested: " + quantityToAdd);
        }

        if (existingCartItemOpt.isPresent()) {
            // Nếu đã có trong giỏ hàng: cập nhật số lượng
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

        // Bước 4: Cập nhật lại số lượng tồn kho của sản phẩm
        int updatedStock = currentStock - quantityToAdd;
        productRepository.updateProductStockQuantity(productId, updatedStock);
    }*/
}
