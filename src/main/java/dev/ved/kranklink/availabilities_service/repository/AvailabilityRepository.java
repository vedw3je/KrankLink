package dev.ved.kranklink.availabilities_service.repository;



import dev.ved.kranklink.availabilities_service.entity.Availability;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface AvailabilityRepository extends MongoRepository<Availability, String> {
    List<Availability> findByDoctorIdAndDayOfWeek(ObjectId doctorId, DayOfWeek dayOfWeek);
    List<Availability> findByDoctorId(ObjectId doctorId);
}
