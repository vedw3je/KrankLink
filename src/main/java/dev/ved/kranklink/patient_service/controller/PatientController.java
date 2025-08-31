package dev.ved.kranklink.patient_service.controller;

import dev.ved.kranklink.patient_service.entity.MedicalRecords;
import dev.ved.kranklink.patient_service.entity.Patient;
import dev.ved.kranklink.patient_service.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Create or update patient
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.savePatient(patient));
    }

    // Get all patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // Get patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable String id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/medical-records")
    public ResponseEntity<List<MedicalRecords>> getMedicalRecords(@PathVariable String id) {
        List<MedicalRecords> records = patientService.getMedicalRecordsByPatientId(id);
        return ResponseEntity.ok(records);
    }


    // Delete patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{patientId}/medical-records")
    public ResponseEntity<MedicalRecords> addMedicalRecord(
            @PathVariable String patientId,
            @RequestBody MedicalRecords medicalRecord) {

        MedicalRecords savedRecord = patientService.addMedicalRecordToPatient(patientId, medicalRecord);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }
}