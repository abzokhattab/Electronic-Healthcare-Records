package com.ehr.controllers;

import java.util.List;

import com.ehr.models.Patient;
import com.ehr.requests.createPatientRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Component
@RequestMapping("api")
@Service
@AllArgsConstructor
@RestController

public class PatientController {

    // Aggregate root
    // tag::get-aggregate-root[]
     @PostMapping("/createDoctor")
    void newDoctor() {
        System.out.print("doctor");

    }

    @PostMapping("/createPatient")
    Patient newEmployee(@RequestBody createPatientRequest newEmployee) {
        System.out.print("patient");
        return null;
    }

    // Single item
    @GetMapping("/employees/{id}")
    Patient one(@PathVariable Long id) {
        return null ;
    }

    @PutMapping("/patients/{id}")
    Patient replaceEmployee(@RequestBody Patient newPatient, @PathVariable Long id) {

return null;
    }

    @DeleteMapping("/patients/{id}")
    void deleteEmployee(@PathVariable Long id) {
    }
}