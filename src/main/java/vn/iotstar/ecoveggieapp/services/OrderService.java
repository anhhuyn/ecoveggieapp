package vn.iotstar.ecoveggieapp.services;

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

}
