package dev.ved.kranklink.doctor_review_service.controller;



import dev.ved.kranklink.doctor_review_service.entity.DoctorReview;
import dev.ved.kranklink.doctor_review_service.service.DoctorReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class DoctorReviewController {

    private final DoctorReviewService reviewService;

    public DoctorReviewController(DoctorReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Submit a review
    @PostMapping
    public ResponseEntity<DoctorReview> addReview(@RequestBody DoctorReview review) {
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    // Fetch all reviews for a doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<DoctorReview>> getReviews(@PathVariable String doctorId) {
        return ResponseEntity.ok(reviewService.getReviewsByDoctor(doctorId));
    }

    // Fetch average rating for a doctor
    @GetMapping("/doctor/{doctorId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable String doctorId) {
        return ResponseEntity.ok(reviewService.getAverageRating(doctorId));
    }
}
