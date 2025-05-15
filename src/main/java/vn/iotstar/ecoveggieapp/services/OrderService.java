package vn.iotstar.ecoveggieapp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.iotstar.ecoveggieapp.models.OrderModel;
import vn.iotstar.ecoveggieapp.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    // Thêm mới đơn hàng và trả về đơn hàng vừa tạo
    @Transactional
    public OrderModel insertOrderService(int customerId, double totalAmount, String paymentMethod,
                                         String status, String note, int addressId) {
        // Thực hiện chèn đơn hàng mới
        orderRepository.insertOrder(customerId, totalAmount, paymentMethod, status, note, addressId);

        // Lấy và trả về đơn hàng mới nhất của khách hàng
        return orderRepository.findLatestOrderByCustomerId(customerId);
    }
    // End of Insert Order Service Method

    public List<Map<String, Object>> getPendingOrdersWithFirstProduct(int customerId) {
        List<Object[]> rawResults = orderRepository.findPendingOrdersWithFirstProductRaw(customerId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : rawResults) {
            Map<String, Object> map = new HashMap<>();
            map.put("orderId", row[0]);
            map.put("totalAmount", row[1]);
            map.put("paymentMethod", row[2]);
            map.put("note", row[3]);
            map.put("createdAt", row[4]);
            map.put("status", row[5]);
            map.put("productName", row[6]);
            map.put("unit", row[7]);
            map.put("price", row[8]);
            map.put("firstProductQuantity", row[9]);
            map.put("productImage", row[10]);
            map.put("totalQuantityInOrder", row[11]);
            map.put("totalItemsInOrder", row[12]);

            result.add(map);
        }

        return result;
    }
    
    public List<Map<String, Object>> getWaitingDeliveryOrdersWithFirstProduct(int customerId) {
        List<Object[]> rawResults = orderRepository.findWaitingDeliveryOrdersWithFirstProductRaw(customerId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : rawResults) {
            Map<String, Object> map = new HashMap<>();
            map.put("orderId", row[0]);
            map.put("totalAmount", row[1]);
            map.put("paymentMethod", row[2]);
            map.put("note", row[3]);
            map.put("createdAt", row[4]);
            map.put("status", row[5]);
            map.put("productName", row[6]);
            map.put("unit", row[7]);
            map.put("price", row[8]);
            map.put("firstProductQuantity", row[9]);
            map.put("productImage", row[10]);
            map.put("totalQuantityInOrder", row[11]);
            map.put("totalItemsInOrder", row[12]);

            result.add(map);
        }

        return result;
    }
    
    // Lấy các đơn hàng "Chờ giao hàng" (Pending Ship)
    public List<Map<String, Object>> getPendingShipOrdersWithFirstProduct(int customerId) {
        List<Object[]> rawResults = orderRepository.findPendingShipOrdersWithFirstProductRaw(customerId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : rawResults) {
            Map<String, Object> map = new HashMap<>();
            map.put("orderId", row[0]);
            map.put("totalAmount", row[1]);
            map.put("paymentMethod", row[2]);
            map.put("note", row[3]);
            map.put("createdAt", row[4]);
            map.put("status", row[5]);
            map.put("productName", row[6]);
            map.put("unit", row[7]);
            map.put("price", row[8]);
            map.put("firstProductQuantity", row[9]);
            map.put("productImage", row[10]);
            map.put("totalQuantityInOrder", row[11]);
            map.put("totalItemsInOrder", row[12]);

            result.add(map);
        }

        return result;
    }

    // Lấy các đơn hàng "Đã giao" (Delivered)
    public List<Map<String, Object>> getDeliveredOrdersWithFirstProduct(int customerId) {
        List<Object[]> rawResults = orderRepository.findDeliveredOrdersWithFirstProductRaw(customerId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : rawResults) {
            Map<String, Object> map = new HashMap<>();
            map.put("orderId", row[0]);
            map.put("totalAmount", row[1]);
            map.put("paymentMethod", row[2]);
            map.put("note", row[3]);
            map.put("createdAt", row[4]);
            map.put("status", row[5]);
            map.put("productName", row[6]);
            map.put("unit", row[7]);
            map.put("price", row[8]);
            map.put("firstProductQuantity", row[9]);
            map.put("productImage", row[10]);
            map.put("totalQuantityInOrder", row[11]);
            map.put("totalItemsInOrder", row[12]);

            result.add(map);
        }

        return result;
    }

    // Lấy các đơn hàng "Đã hủy" (Cancel)
    public List<Map<String, Object>> getCanceledOrdersWithFirstProduct(int customerId) {
        List<Object[]> rawResults = orderRepository.findCanceledOrdersWithFirstProductRaw(customerId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : rawResults) {
            Map<String, Object> map = new HashMap<>();
            map.put("orderId", row[0]);
            map.put("totalAmount", row[1]);
            map.put("paymentMethod", row[2]);
            map.put("note", row[3]);
            map.put("createdAt", row[4]);
            map.put("status", row[5]);
            map.put("productName", row[6]);
            map.put("unit", row[7]);
            map.put("price", row[8]);
            map.put("firstProductQuantity", row[9]);
            map.put("productImage", row[10]);
            map.put("totalQuantityInOrder", row[11]);
            map.put("totalItemsInOrder", row[12]);

            result.add(map);
        }

        return result;
    }
    
    public int countOrdersByStatus(int customerId, String status) {
        return orderRepository.countOrdersByStatus(customerId, status);
    }


}
