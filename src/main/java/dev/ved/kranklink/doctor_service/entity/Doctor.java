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
    private String doctorId;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String phoneNumber;

    private String name;
    private String specialization;
    private String qualification;
    private int experienceYears;

    private String hospitalName;
    private String hospitalAddress;

    private consultationType consultationType; // ONLINE / OFFLINE / BOTH
    private Double consultationFee;

    private String city;

    private boolean available; // Is doctor currently available for appointments?
}

