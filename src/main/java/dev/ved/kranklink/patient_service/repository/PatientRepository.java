package dev.ved.kranklink.patient_service.repository;

import dev.ved.kranklink.patient_service.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    // Additional query methods if needed
}
