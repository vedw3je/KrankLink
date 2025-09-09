package dev.ved.kranklink.doctor_review_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "doctor_reviews")
public class DoctorReview {

    @Id
    private String id;

    private String doctorId;     // ObjectId as String
    private String patientId;    // ObjectId as String

    private int rating;          // 1-5
    private String comment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private Date createdAt;
}