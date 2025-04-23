package vn.iotstar.ecoveggieapp.controllers;

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
}
