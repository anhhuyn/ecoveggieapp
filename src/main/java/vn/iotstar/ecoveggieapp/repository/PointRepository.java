package vn.iotstar.ecoveggieapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import vn.iotstar.ecoveggieapp.models.PointModel;

@Repository
public interface PointRepository extends CrudRepository<PointModel, Integer> {

    // Truy vấn lấy danh sách point theo user_id
    @Query(value = "SELECT * FROM points WHERE user_id = :user_id", nativeQuery = true)
    List<PointModel> findPointsByUserId(@Param("user_id") int userId);
    
    // Update điểm cho user
    @Modifying
    @Transactional
    @Query(value = "UPDATE points SET total_points = :total_points, count_day = CASE " +
                    "WHEN count_day >= 6 THEN 0 " +
                    "ELSE count_day + 1 " +
                    "END, updated_at = GETDATE() WHERE user_id = :user_id", nativeQuery = true)
    void updatePoint(@Param("user_id") int userId, @Param("total_points") int totalPoints);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO points (user_id, total_points, created_at, updated_at, count_day) " +
                   "VALUES (:user_id, 100, GETDATE(), GETDATE(), 0)", nativeQuery = true)
    void insertPoint(@Param("user_id") int userId);

}