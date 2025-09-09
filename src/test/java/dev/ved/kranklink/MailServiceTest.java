package dev.ved.kranklink;

import dev.ved.kranklink.appointment_reminder_service.dart.MailService;
import dev.ved.kranklink.appointment_service.entity.Appointment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendAppointmentReminder() {
        // Create a dummy appointment
        Appointment appt = new Appointment();
        appt.setAppointmentDate(new Date());
        appt.setAppointmentTime("21:00");
        appt.setReason("General Checkup");

        // Replace with a real email you can test with
        mailService.sendAppointmentReminder("soulveds02@gmail.com", appt);
    }
}