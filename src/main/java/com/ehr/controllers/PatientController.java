package com.ehr.controllers;

import java.util.List;

import com.ehr.models.Patient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PatientController {

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/patients")
    List<Patient> all() {
return null ;
    }

    @PostMapping("/patients")
    Patient newEmployee(@RequestBody Patient newEmployee) {
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