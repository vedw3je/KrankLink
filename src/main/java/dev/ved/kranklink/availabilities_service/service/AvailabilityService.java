package dev.ved.kranklink.availabilities_service.service;

import dev.ved.kranklink.availabilities_service.entity.Availability;
import dev.ved.kranklink.availabilities_service.repository.AvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public List<Availability> getDoctorAvailability(String doctorId, DayOfWeek dayOfWeek) {
        return availabilityRepository.findByDoctorIdAndDayOfWeek(new ObjectId(doctorId), dayOfWeek);
    }

    public List<Availability> getAllDoctorAvailability(String  doctorId) {
        return availabilityRepository.findByDoctorId(new ObjectId(doctorId));
    }

    public Availability saveAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    public boolean isDoctorAvailable(String doctorId, Date date, String time) {
        // Extract day of week
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        // Fetch slots for that doctor + day
        List<Availability> availabilities = getDoctorAvailability(doctorId, dayOfWeek);

        if (availabilities.isEmpty()) return false;

        LocalTime appointmentTime = LocalTime.parse(time);

        // Check if appointmentTime falls within any availability slot
        return availabilities.stream()
                .anyMatch(a -> !appointmentTime.isBefore(a.getStartTime()) && !appointmentTime.isAfter(a.getEndTime()));
    }

}
