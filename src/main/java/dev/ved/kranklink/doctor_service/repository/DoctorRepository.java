package dev.ved.kranklink.doctor_service.repository;

import dev.ved.kranklink.doctor_service.entity.Doctor;

import dev.ved.kranklink.doctor_service.enums.consultationType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor,String> {

    public Optional<Doctor> findByPhoneNumber(String phoneNumber);

    public Optional<Doctor> findByEmail(String email);

    public List<Doctor> findByName(String name);
    List<Doctor> findByNameRegexIgnoreCase(String regex);

    public List<Doctor> findByConsultationType(consultationType consultationType );

}
