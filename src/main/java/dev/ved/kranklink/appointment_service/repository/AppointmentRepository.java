package dev.ved.kranklink.appointment_service.repository;


import dev.ved.kranklink.appointment_service.entity.Appointment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {


    List<Appointment> findByDoctorId(ObjectId doctorId);
    List<Appointment> findByPatientId(ObjectId  patientId);
    List<Appointment> findByDoctorIdAndStatus(ObjectId  doctorId, String status);
    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTime(ObjectId doctorId, Date appointmentDate, String appointmentTime);
    // doctor + date
    List<Appointment> findByDoctorIdAndAppointmentDate(ObjectId  doctorId, Date appointmentDate);

    List<Appointment> findByAppointmentDateBetweenAndReminderSent(Date start, Date end, boolean reminderSent);
}
