package dev.ved.kranklink.patient_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data  // generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor  // generates no-arg constructor
@AllArgsConstructor // generates all-args constructor
@Document(collection = "patients")
public class Patient {

    @Id
    private String id;

    private String name;
    private int age;
    private String gender;
    private String contactInfo;

    private String userId;


    // Embedded medical records
   private List<MedicalRecords> medicalRecords;
}
