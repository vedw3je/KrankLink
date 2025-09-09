package dev.ved.kranklink.appointment_service.service;

import dev.ved.kranklink.appointment_service.entity.Appointment;
import dev.ved.kranklink.appointment_service.enums.AppointmentStatus;
import dev.ved.kranklink.appointment_service.repository.AppointmentRepository;
import dev.ved.kranklink.availabilities_service.service.AvailabilityService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AvailabilityService availabilityService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, AvailabilityService availabilityService) {
        this.appointmentRepository = appointmentRepository;
        this.availabilityService = availabilityService;
    }

    public Appointment createAppointment(Appointment appointment) {
        // 1. Check if doctor is available
        boolean isAvailable = availabilityService
                .isDoctorAvailable(String.valueOf(appointment.getDoctorId()), appointment.getAppointmentDate(), appointment.getAppointmentTime());

        if (!isAvailable) {
            throw new RuntimeException("Doctor is not available at the selected time");
        }


        boolean exists = appointmentRepository.existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime()
        );

        if (exists) {
            throw new RuntimeException("Appointment slot already booked for this doctor");
        }




        appointment.setCreatedAt(new Date());
        appointment.setUpdatedAt(new Date());
        appointment.setStatus(AppointmentStatus.PENDING);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        return appointmentRepository.findByDoctorId(new ObjectId(doctorId));
    }

    public List<Appointment> getAppointmentsByPatient(String patientId) {
        return appointmentRepository.findByPatientId(new ObjectId(patientId));
    }

    public List<Appointment> getAppointmentsByDoctorAndStatus(String doctorId, String status) {
        return appointmentRepository.findByDoctorIdAndStatus(new ObjectId(doctorId), status);
    }

    public List<Appointment> getAppointmentsByDoctorAndDate(String  doctorId, Date appointmentDate) {
        return appointmentRepository.findByDoctorIdAndAppointmentDate(new ObjectId(doctorId), appointmentDate);
    }


    public Appointment updateAppointment(String id, Appointment updatedAppointment) {
        return appointmentRepository.findById(id).map(appt -> {
            appt.setAppointmentDate(updatedAppointment.getAppointmentDate());
            appt.setAppointmentTime(updatedAppointment.getAppointmentTime());
            appt.setReason(updatedAppointment.getReason());
            appt.setStatus(updatedAppointment.getStatus());
            appt.setUpdatedAt(new Date());
            return appointmentRepository.save(appt);
        }).orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }


}
