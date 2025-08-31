package dev.ved.kranklink.patient_service.service;


import dev.ved.kranklink.patient_service.entity.MedicalRecords;
import dev.ved.kranklink.patient_service.entity.Patient;
import dev.ved.kranklink.patient_service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private final PatientRepository patientRepository;



    public PatientService(PatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }

    public Patient savePatient(Patient patient) {

        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {

        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }

    public List<MedicalRecords> getMedicalRecordsByPatientId(String patientId) {
        return patientRepository.findById(patientId)
                .map(Patient::getMedicalRecords)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));
    }

    public MedicalRecords addMedicalRecordToPatient(String patientId, MedicalRecords newRecord) {
        return patientRepository.findById(patientId).map(patient -> {
            if (patient.getMedicalRecords() == null) {
                patient.setMedicalRecords(new ArrayList<>());
            }
            newRecord.setCreatedAt(new Date());
            newRecord.setUpdatedAt(new Date());
            patient.getMedicalRecords().add(newRecord);
            patientRepository.save(patient);
            return newRecord;
        }).orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));
    }


    public MedicalRecords updateMedicalRecord(String patientId, String recordId, MedicalRecords updatedRecord) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));

        MedicalRecords existingRecord = patient.getMedicalRecords().stream()
                .filter(r -> r.getRecordId().equals(recordId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Medical record not found with id: " + recordId));

        // Update only mutable fields
        existingRecord.setRecordId(updatedRecord.getRecordId());
        existingRecord.setDiagnosis(updatedRecord.getDiagnosis());
        existingRecord.setPdfReportUrl(updatedRecord.getPdfReportUrl());
        existingRecord.setDoctorId(updatedRecord.getDoctorId());
        existingRecord.setUpdatedAt(new Date());

        patientRepository.save(patient);

        return existingRecord;
    }
}