package com.ehr.controllers;

import com.ehr.Block;
import com.ehr.BlockChain;
import com.ehr.models.Doctor;
import com.ehr.models.Patient;
import com.ehr.models.Visit;
import com.ehr.requests.createPatientRequest;
import com.ehr.requests.createVisitRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("id", d.id);
        return p;

    }

    @PostMapping("/createPatient")
    Block newEmployee(@RequestBody createPatientRequest newPatient, @RequestParam("doctor_id") String id) throws Exception {
        Doctor d = null;
        for (int i = 0; i < doc_list.size(); i++) {
            if (doc_list.get(i).id.equals(id))
                d = doc_list.get(i);
        }
        Patient p = Patient.builder().height(newPatient.height()).age(newPatient.age()).id(UUID.randomUUID().toString()).bloodPressure(newPatient.bloodPressure()).name(newPatient.name()).build();
        //  Patient p = Patient.builder().name(newPatient.name()).id(UUID.randomUUID().toString()).build();


        Block b = d.addPatient(p);
        System.out.println(b);
        return b;

    }


    @PostMapping("/addVisit")
    Object addVisit(@RequestBody createVisitRequest visit, @RequestParam("doctor_id") String doc_id, @RequestParam("patient_id") String patient_id) throws Exception {
        Doctor d = null;
        for (int i = 0; i < doc_list.size(); i++) {
            if (doc_list.get(i).id.equals(doc_id))
                d = doc_list.get(i);
        }

        Visit v = Visit.builder().bloodPressure(visit.bloodPressure()).pulse(visit.pulse()).glucose(visit.glucose()).reason(visit.reason()).diagnosis(visit.diagnosis()).prescription(visit.prescription()).build();

        System.out.println(v);

        if (d==null) return "Unauthorized action";
        return d.addVisit(patient_id, v);

    }

    //TODO
    @GetMapping("/getPatientInfo")
    Object replaceEmployee(@RequestParam("patient_id") String id, @RequestParam(value ="doctor_id",required=false) String doc_id) throws Exception {


        Doctor d = null;
        if (doc_id != null) {
            for (int i = 0; i < doc_list.size(); i++) {
                if (doc_list.get(i).id.equals(doc_id))
                    d = doc_list.get(i);
            }
        } else {
            for (int i = 0; i < doc_list.size(); i++) {
                if (doc_list.get(i).getPatientsKeys().get(id) != null)
                    d = doc_list.get(i);
            }
        }

        if (d == null){
            return "Unauthorized action";
        }
        return d.getPatient(id);
    }


    @GetMapping("/getBlocks")
    ArrayList<Block> newVisit() {
        return BlockChain.getInstance().blocks;

    }

}