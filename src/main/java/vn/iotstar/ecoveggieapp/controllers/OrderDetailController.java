package vn.iotstar.ecoveggieapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.iotstar.ecoveggieapp.services.OrderDetailService;

@RestController
@RequestMapping("/api/v1")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    // Thêm chi tiết đơn hàng
    @PostMapping("/order/detail/create")
    public ResponseEntity<String> createOrderDetail(
            @RequestParam("order_id") int orderId,
            @RequestParam("product_id") int productId,
            @RequestParam("quantity") int quantity,
            @RequestParam("price") double price
    ) {
        try {
            // Gọi service để thêm chi tiết đơn hàng
            orderDetailService.insertOrderDetailService(orderId, productId, quantity, price);
            return new ResponseEntity<>("successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }
 // Lấy chi tiết đầy đủ của đơn hàng theo orderId
    @GetMapping("/order/detail/{orderId}")
    public ResponseEntity<?> getOrderDetailByOrderId(@PathVariable("orderId") int orderId) {
        try {
            List<Map<String, Object>> details = orderDetailService.getFullOrderDetailByOrderId(orderId);
            if (details.isEmpty()) {
                return new ResponseEntity<>("Không tìm thấy đơn hàng", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(details, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi khi lấy chi tiết đơn hàng", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
