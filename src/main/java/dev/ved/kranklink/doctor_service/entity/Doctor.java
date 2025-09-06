package dev.ved.kranklink.doctor_service.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import dev.ved.kranklink.doctor_service.enums.consultationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "doctors")
public class Doctor {

    @Id
    private String id;

    @Indexed(unique = true)
    private String doctorId; // Unique identifier for the doctor (like emp code / hospital code)

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String phoneNumber;

    private String name;
    private String specialization; // e.g., Cardiologist, Neurologist
    private String qualification;  // e.g., MBBS, MD, MS
    private int experienceYears;   // Years of practice

    private String hospitalName;
    private String hospitalAddress;

    private consultationType consultationType; // ONLINE / OFFLINE / BOTH
    private Double consultationFee;

    private boolean available; // Is doctor currently available for appointments?
}

