package vn.iotstar.ecoveggieapp.services;

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
}
