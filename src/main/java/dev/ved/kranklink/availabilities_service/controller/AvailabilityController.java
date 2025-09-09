package dev.ved.kranklink.availabilities_service.controller;




import dev.ved.kranklink.availabilities_service.entity.Availability;
import dev.ved.kranklink.availabilities_service.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    // Add availability for a doctor
    @PostMapping
    public ResponseEntity<Availability> createAvailability(@RequestBody Availability availability) {
        return ResponseEntity.ok(availabilityService.saveAvailability(availability));
    }

    // Get availability for a doctor for specific day
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable String doctorId,
            @RequestParam(required = false) DayOfWeek dayOfWeek) {

        List<Availability> slots = (dayOfWeek != null)
                ? availabilityService.getDoctorAvailability(doctorId, dayOfWeek)
                : availabilityService.getAllDoctorAvailability(doctorId);

        if (slots.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(slots);
    }
}
