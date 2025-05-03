package vn.iotstar.ecoveggieapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import vn.iotstar.ecoveggieapp.models.ReviewMediaModel;
import vn.iotstar.ecoveggieapp.services.ReviewMediaService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/review-media")
public class ReviewMediaController {

    @Autowired
    private ReviewMediaService reviewMediaService;

    /**
     * Thêm hình ảnh vào review
     * 
     * @param reviewId   ID của review
     * @param media      Hình ảnh cần tải lên
     * @param mediaType  Loại media (image, video)
     * @return ResponseEntity với status CREATED hoặc BAD_REQUEST
     */
    @PostMapping("/addImage")
    public ResponseEntity<String> addReviewMediaImage(
            @RequestParam("reviewId") int reviewId,
            @RequestParam("media") MultipartFile media,
            @RequestParam("mediaType") String mediaType) {

        // Xử lý lưu hình ảnh
        String mediaUrl = saveReviewMedia(media);
        if (mediaUrl == null) {
            return new ResponseEntity<>("Failed to upload media", HttpStatus.BAD_REQUEST);
        }

        // Thêm media vào review
        ReviewMediaModel reviewMedia = reviewMediaService.addReviewMedia(reviewId, mediaUrl, mediaType);
        if (reviewMedia == null) {
            return new ResponseEntity<>("Review not found", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Media added successfully", HttpStatus.CREATED);
    }

    /**
     * Lưu hình ảnh lên thư mục server và trả về URL
     * 
     * @param media      Hình ảnh cần lưu
     * @return URL của hình ảnh hoặc null nếu lỗi
     */
    private String saveReviewMedia(MultipartFile media) {
        String uploadDir = System.getProperty("user.dir") + "/uploads/review_media/";
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        String fileName = media.getOriginalFilename();
        String filePath = uploadDir + fileName;

        try {
            media.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return fileName;
    }
}
