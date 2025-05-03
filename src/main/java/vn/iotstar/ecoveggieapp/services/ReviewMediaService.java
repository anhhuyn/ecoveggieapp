package vn.iotstar.ecoveggieapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.iotstar.ecoveggieapp.models.ReviewMediaModel;
import vn.iotstar.ecoveggieapp.models.ReviewModel;
import vn.iotstar.ecoveggieapp.repository.ReviewMediaRepository;
import vn.iotstar.ecoveggieapp.repository.ReviewRepository;

import java.util.Date;

@Service
public class ReviewMediaService {

    @Autowired
    private ReviewMediaRepository reviewMediaRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * 
     *
     * @param reviewId   
     * @param mediaUrl   
     * @param mediaType   
     * @return ReviewMediaModel 
     */
    @Transactional
    public ReviewMediaModel addReviewMedia(int reviewId, String mediaUrl, String mediaType) {
        ReviewModel review = reviewRepository.findById(reviewId).orElse(null);

        if (review == null) {
            return null; 
        }

        ReviewMediaModel reviewMedia = new ReviewMediaModel();
        reviewMedia.setReview(review); // Gắn Review cho Media
        reviewMedia.setMediaUrl(mediaUrl); // Đường dẫn media
        reviewMedia.setMediaType(mediaType); // Loại media (image, video...)
        reviewMedia.setUploadedAt(new Date()); // Thời gian tải lên

        return reviewMediaRepository.save(reviewMedia);
    }
}
