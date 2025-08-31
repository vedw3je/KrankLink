package dev.ved.kranklink.patient_service.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecords {


    @Id
    private String recordId;
    private String doctorId;
    private String doctorName;
    private String specialization;

    private String diagnosis;
    private String treatment;
    private List<String> medications;
    private String symptoms;
    private String allergies;

    private String hospitalName;
    private String department;
    private String visitType;

    private String dateOfVisit;
    private Date createdAt;
    private Date updatedAt;


    private String pdfReportUrl;
}
