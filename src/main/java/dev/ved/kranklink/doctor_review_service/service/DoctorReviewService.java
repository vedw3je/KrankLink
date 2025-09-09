package dev.ved.kranklink.doctor_review_service.service;


import dev.ved.kranklink.doctor_review_service.entity.DoctorReview;
import dev.ved.kranklink.doctor_review_service.repository.DoctorReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;

@Service
public class DoctorReviewService {

    private final DoctorReviewRepository reviewRepository;

    public DoctorReviewService(DoctorReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Submit a review
    public DoctorReview addReview(DoctorReview review) {
        review.setCreatedAt(new Date());
        return reviewRepository.save(review);
    }

    // Fetch all reviews for a doctor
    public List<DoctorReview> getReviewsByDoctor(String doctorId) {
        return reviewRepository.findByDoctorId(doctorId);
    }

    // Fetch average rating for a doctor
    public double getAverageRating(String doctorId) {
        List<DoctorReview> reviews = reviewRepository.findByDoctorId(doctorId);
        OptionalDouble avg = reviews.stream().mapToInt(DoctorReview::getRating).average();
        return avg.orElse(0.0);
    }
}
