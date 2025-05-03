package vn.iotstar.ecoveggieapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import vn.iotstar.ecoveggieapp.models.ProductModel;
import vn.iotstar.ecoveggieapp.models.ReviewModel;
import vn.iotstar.ecoveggieapp.services.ProductService;
import vn.iotstar.ecoveggieapp.services.ReviewService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private ProductService productService;

    /**
     * Lấy danh sách sản phẩm đã mua nhưng chưa được đánh giá bởi user
     * 
     * @param userId ID người dùng
     * @return danh sách sản phẩm chưa đánh giá
     */
    @GetMapping("/unreviewed")
    public ResponseEntity<List<ProductModel>> getUnreviewedProducts(@RequestParam("userId") int userId) {
        List<ProductModel> unreviewedProducts = reviewService.getUnreviewedProductsByUser(userId);
        return new ResponseEntity<>(unreviewedProducts, HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<ReviewModel> addReview(@RequestParam("userId") int userId,
                                                 @RequestParam("productId") int productId,
                                                 @RequestParam("rating") int rating,
                                                 @RequestParam("comment") String comment) {
        ReviewModel review = reviewService.addReview(userId, productId, rating, comment);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Lấy danh sách đánh giá của sản phẩm, bao gồm user và media
     *
     * @param productId ID sản phẩm
     * @return danh sách đánh giá
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewModel>> getReviewsByProductId(@PathVariable("productId") int productId) {
        List<ReviewModel> reviews = reviewService.getReviewsByProductIdWithUserAndMedia(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

   
}
