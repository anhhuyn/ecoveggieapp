package vn.iotstar.ecoveggieapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.iotstar.ecoveggieapp.models.PointModel;
import vn.iotstar.ecoveggieapp.services.PointService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PointController {

    @Autowired
    private PointService pointService;

    // Lấy danh sách điểm của người dùng
    @GetMapping("/points/{userId}")
    public ResponseEntity<List<PointModel>> getPointsByUserId(@PathVariable("userId") int userId) {
        List<PointModel> points = pointService.getPointsByUserId(userId);

        if (points.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Nếu không có điểm, trả về HTTP 204 No Content
        }

        return new ResponseEntity<>(points, HttpStatus.OK); // Trả về danh sách điểm với HTTP 200 OK
    }
    
 // Thêm điểm cho người dùng
    @PostMapping("/points/update")
    public ResponseEntity<String> addPoint(
    		 @RequestParam("user_id") int userId,
    	        @RequestParam("total_points") int totalPoints) {
        try {
            pointService.updatePointForUser(userId, totalPoints);
            return new ResponseEntity<>("1", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi khi thêm điểm: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 // Thêm điểm mới cho người dùng
    @PostMapping("/points/insert")
    public ResponseEntity<String> insertPoint(
            @RequestParam("user_id") int userId) {
        try {
            pointService.insertPointForUser(userId);
            return new ResponseEntity<>("1", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi khi thêm mới điểm: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}