package vn.iotstar.ecoveggieapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.iotstar.ecoveggieapp.models.OrderDetailModel;
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
    
    @GetMapping("orders/pending/{customerId}")
    public ResponseEntity<List<Map<String, Object>>> getPendingOrders(
            @PathVariable int customerId) {
        List<Map<String, Object>> result = orderService.getPendingOrdersWithFirstProduct(customerId);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("orders/waiting_delivery/{customerId}")
    public ResponseEntity<List<Map<String, Object>>> getWaitingDeliver(
            @PathVariable int customerId) {
        List<Map<String, Object>> result = orderService.getWaitingDeliveryOrdersWithFirstProduct(customerId);
        return ResponseEntity.ok(result);
    }
    
 // Lấy các đơn hàng "Chờ giao hàng" (Pending Ship)
    @GetMapping("orders/pending_ship/{customerId}")
    public ResponseEntity<List<Map<String, Object>>> getPendingShipOrders(
            @PathVariable int customerId) {
        List<Map<String, Object>> result = orderService.getPendingShipOrdersWithFirstProduct(customerId);
        return ResponseEntity.ok(result);
    }

    // Lấy các đơn hàng "Đã giao" (Delivered)
    @GetMapping("orders/delivered/{customerId}")
    public ResponseEntity<List<Map<String, Object>>> getDeliveredOrders(
            @PathVariable int customerId) {
        List<Map<String, Object>> result = orderService.getDeliveredOrdersWithFirstProduct(customerId);
        return ResponseEntity.ok(result);
    }

    // Lấy các đơn hàng "Đã hủy" (Cancel)
    @GetMapping("orders/canceled/{customerId}")
    public ResponseEntity<List<Map<String, Object>>> getCanceledOrders(
            @PathVariable int customerId) {
        List<Map<String, Object>> result = orderService.getCanceledOrdersWithFirstProduct(customerId);
        return ResponseEntity.ok(result);
    }
}
