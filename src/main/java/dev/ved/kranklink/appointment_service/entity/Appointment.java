package dev.ved.kranklink.appointment_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.ved.kranklink.appointment_service.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "appointments")
public class Appointment {

    @Id
    private String id;

    private ObjectId patientId;
    private ObjectId doctorId;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY", timezone = "UTC")
    private Date appointmentDate;
    private String appointmentTime;
    private String reason;
    private AppointmentStatus status;

    private Date createdAt;
    private Date updatedAt;

    private boolean reminderSent = false;
}

