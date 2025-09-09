package dev.ved.kranklink.appointment_reminder_service.dart;

import dev.ved.kranklink.appointment_service.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendAppointmentReminder(String toEmail, Appointment appointment) {
        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(toEmail);
//            message.setSubject("Appointment Reminder");
//            message.setText(
//                    "Dear Patient,\n\n" +
//                            "This is a reminder for your upcoming appointment.\n\n" +
//                            "Date: " + new SimpleDateFormat("dd-MM-yyyy").format(appointment.getAppointmentDate()) + "\n" +
//                            "Time: " + appointment.getAppointmentTime() + "\n" +
//                            "Reason: " + appointment.getReason() + "\n\n" +
//                            "Please arrive 10 minutes early.\n\n" +
//                            "Regards,\nKranklink"
//            );
//
//            mailSender.send(message);
            System.out.println("Reminder sent to: " + toEmail);

        } catch (Exception e) {
            System.err.println("Failed to send reminder to: " + toEmail);
            e.printStackTrace();
        }
    }
}
