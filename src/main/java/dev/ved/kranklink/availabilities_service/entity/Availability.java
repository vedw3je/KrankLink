package dev.ved.kranklink.availabilities_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Document(collection = "availabilities")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Availability {

    @Id
    private String id;

    private ObjectId doctorId;

    private DayOfWeek dayOfWeek;

    private LocalTime startTime;
    private LocalTime endTime;
    private int slotDurationMinutes;
}
