package dev.ved.kranklink.doctor_review_service.repository;

import dev.ved.kranklink.doctor_review_service.entity.DoctorReview;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoctorReviewRepository extends MongoRepository<DoctorReview, String> {

    List<DoctorReview> findByDoctorId(String doctorId);

}