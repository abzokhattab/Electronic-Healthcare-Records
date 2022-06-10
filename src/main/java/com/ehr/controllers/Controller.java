package com.ehr.controllers;

import com.ehr.Block;
import com.ehr.models.Doctor;
import com.ehr.models.Patient;
import com.ehr.requests.createPatientRequest;
import com.ehr.requests.createVisitRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import com.google.gson.Gson;

@Slf4j
@Component
@RequestMapping("api")
@Service
@AllArgsConstructor
@RestController

public class Controller {

    public static ArrayList<Doctor> doc_list = new ArrayList<Doctor>();
    // Aggregate root
    // tag::get-aggregate-root[]
    @PostMapping("/createDoctor")
    HashMap<String, String> newDoctor() throws Exception {
        Doctor d = new Doctor();
        doc_list.add(d);
        HashMap<String,String>  p = new HashMap<String,String>();
        p.put("id",d.id);
        return p;

    }

    @PostMapping("/createPatient")
    Block newEmployee(@RequestBody createPatientRequest newPatient, @RequestParam("doctor_id") String id) throws Exception {
      Doctor d  = null ;
      for (int i =  0 ; i<doc_list.size();i++)
      {
          if (doc_list.get(i).id.equals(id))
              d = doc_list.get(i);
      }
         Patient p = Patient.builder().height(newPatient.height()).age(newPatient.age()).id(UUID.randomUUID().toString()).bloodPressure(newPatient.bloodPressure()).name(newPatient.name()).build();
      //  Patient p = Patient.builder().name(newPatient.name()).id(UUID.randomUUID().toString()).build();


        Block b = d.addPatient( p);
        System.out.println(b);
        return b ;

    }

    //TODO
    @GetMapping("/patients/{id}")
    Patient replaceEmployee( @PathVariable String id) {
    Doctor d = doc_list.get(0);
        Gson gson = new Gson();
        Patient p = gson.fromJson(d.getPatient(id), Patient.class);

        return   p ;



   }

    @PostMapping("/createVisit")
    void newVisit(@RequestBody createVisitRequest newVisit) {
        System.out.print("visit");
        System.out.print(newVisit);

    }

    // Single item
    @GetMapping("/employees/{id}")
    Patient one(@PathVariable Long id) {
        return null;
    }



    @DeleteMapping("/patients/{id}")
    void deleteEmployee(@PathVariable Long id) {
    }
}