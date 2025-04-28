package vn.iotstar.ecoveggieapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.iotstar.ecoveggieapp.models.PointModel;
import vn.iotstar.ecoveggieapp.repository.PointRepository;

import java.util.List;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    // Lấy danh sách điểm của người dùng theo user_id
    public List<PointModel> getPointsByUserId(int userId) {
        return pointRepository.findPointsByUserId(userId);
    }
    
 // Thêm điểm tích lũy mới cho user
    @Transactional
    public void updatePointForUser(int userId, int totalPoints) {
        pointRepository.updatePoint(userId, totalPoints);
    }
    
 // Thêm điểm mới cho user
    @Transactional
    public void insertPointForUser(int userId) {
        pointRepository.insertPoint(userId);
    }
    
    // Reset total_points = 0 cho user
    @Transactional
    public void resetTotalPointsForUser(int userId) {
        pointRepository.resetTotalPoints(userId);
    }

}