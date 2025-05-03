package vn.iotstar.ecoveggieapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import vn.iotstar.ecoveggieapp.models.ProductModel;
import vn.iotstar.ecoveggieapp.models.ReviewMediaModel;
import vn.iotstar.ecoveggieapp.models.ReviewModel;
import vn.iotstar.ecoveggieapp.models.UserModel;
import vn.iotstar.ecoveggieapp.repository.ProductRepository;
import vn.iotstar.ecoveggieapp.repository.ReviewMediaRepository;
import vn.iotstar.ecoveggieapp.repository.ReviewRepository;
import vn.iotstar.ecoveggieapp.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMediaRepository reviewMediaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;  // Cấu hình đường dẫn lưu tệp

    /**
     * Lấy danh sách các sản phẩm đã mua nhưng chưa được đánh giá bởi user
     *
     * @param userId id của người dùng
     * @return danh sách sản phẩm chưa đánh giá
     */
    public List<ProductModel> getUnreviewedProductsByUser(int userId) {
        return reviewRepository.getUnreviewedProductsByUser(userId);
    }

 // Thêm đánh giá mới và trả về đối tượng ReviewModel đã lưu
    @Transactional
    public ReviewModel addReview(int userId, int productId, int rating, String comment) {
        UserModel user = userRepository.findById(userId).orElse(null);
        ProductModel product = productRepository.findById(productId).orElse(null);

        if (user == null || product == null) {
            return null; // hoặc ném exception tùy theo cách xử lý lỗi
        }

        ReviewModel review = new ReviewModel();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(rating);
        review.setComment(comment);
        review.setCreatedAt(new Date());

        return reviewRepository.save(review);
    }
    

    /**
     * Lấy danh sách đánh giá theo productId, bao gồm cả thông tin user và media
     *
     * @param productId id sản phẩm
     * @return danh sách review
     */
    public List<ReviewModel> getReviewsByProductIdWithUserAndMedia(int productId) {
        return reviewRepository.findReviewsByProductIdWithUserAndMedia(productId);
    }


}
