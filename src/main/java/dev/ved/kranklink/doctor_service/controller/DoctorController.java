package dev.ved.kranklink.doctor_service.controller;


import dev.ved.kranklink.doctor_service.entity.Doctor;
import dev.ved.kranklink.doctor_service.service.DoctorService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    // Constructor injection (preferred)
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors (){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor){
        return ResponseEntity.ok(doctorService.saveDoctor(doctor));
    }

    @GetMapping("/searchByName")
    public ResponseEntity<?> findDoctorsByName(@RequestParam String name) {
        List<Doctor> doctors = doctorService.getDoctorsByName(name);
        if (doctors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No doctors found with name: " + name);
        }
        return ResponseEntity.ok(doctors);
    }

}
