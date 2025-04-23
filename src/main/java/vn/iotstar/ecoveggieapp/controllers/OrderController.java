package vn.iotstar.ecoveggieapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.iotstar.ecoveggieapp.models.OrderModel;
import vn.iotstar.ecoveggieapp.services.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Tạo đơn hàng mới và trả về OrderModel
    @PostMapping("/order/create")
    public ResponseEntity<OrderModel> createOrder(
            @RequestParam("customer_id") int customerId,
            @RequestParam("total_amount") double totalAmount,
            @RequestParam("payment_method") String paymentMethod,
            @RequestParam("status") String status,
            @RequestParam(value = "note", required = false) String note,
            @RequestParam("address_id") int addressId
    ) {
        // Gọi service để tạo đơn hàng và lấy về OrderModel
        OrderModel order = orderService.insertOrderService(customerId, totalAmount, paymentMethod, status, note, addressId);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
