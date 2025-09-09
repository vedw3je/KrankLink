package dev.ved.kranklink.appointment_reminder_service.dart;

import dev.ved.kranklink.appointment_service.entity.Appointment;
import dev.ved.kranklink.appointment_service.repository.AppointmentRepository;
import dev.ved.kranklink.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentReminderService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final MailService mailService;

    @Autowired
    public AppointmentReminderService(AppointmentRepository appointmentRepository,
                                      UserRepository userRepository,
                                      MailService mailService) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @Scheduled(fixedRate = 5 * 60 * 1000) // every 5 minutes
    public void sendReminders() {
        Date now = new Date();
        Date twoHoursLater = new Date(now.getTime() + 2 * 60 * 60 * 1000);

        // Find appointments starting in 2 hours and reminder not sent
        List<Appointment> upcomingAppointments = appointmentRepository
                .findByAppointmentDateBetweenAndReminderSent(now, twoHoursLater, false);

        for (Appointment appt : upcomingAppointments) {
            // Get patient email
            userRepository.findById(String.valueOf(appt.getPatientId())).ifPresent(patient -> {
                mailService.sendAppointmentReminder(patient.getEmail(), appt);
                appt.setReminderSent(true);
                appointmentRepository.save(appt); // mark reminder sent
            });
        }
    }
}
