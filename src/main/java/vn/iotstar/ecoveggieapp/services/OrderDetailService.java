package vn.iotstar.ecoveggieapp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.iotstar.ecoveggieapp.repository.OrderDetailRepository;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // Thêm chi tiết đơn hàng
    @Transactional
    public void insertOrderDetailService(int orderId, int productId, int quantity, double price) {
        orderDetailRepository.insertOrderDetail(orderId, productId, quantity, price);
    }
 // Lấy chi tiết đầy đủ của đơn hàng theo orderId
    public List<Map<String, Object>> getFullOrderDetailByOrderId(int orderId) {
        List<Object[]> rawResults = orderDetailRepository.findFullOrderDetailByOrderId(orderId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : rawResults) {
            Map<String, Object> map = new HashMap<>();
            map.put("orderId", row[0]);
            map.put("customerId", row[1]);
            map.put("totalAmount", row[2]);
            map.put("paymentMethod", row[3]);
            map.put("status", row[4]);
            map.put("note", row[5]);
            map.put("createdAt", row[6]);

            map.put("addressLine", row[7]);
            map.put("ward", row[8]);
            map.put("district", row[9]);
            map.put("province", row[10]);
            map.put("receiverPhone", row[11]);

            map.put("productName", row[12]);
            map.put("unit", row[13]);
            map.put("price", row[14]);
            map.put("quantity", row[15]);
            map.put("productId", row[16]);  // Added productId
            map.put("productImage", row[17]);

            result.add(map);
        }

        return result;
    }
}
