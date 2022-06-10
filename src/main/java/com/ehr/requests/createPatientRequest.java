package com.ehr.requests;

public record createPatientRequest(  String name,
                                     int age,
                                     float weight,
                                     float height,
                                     String sex,
                                     int bloodPressure,
                                     int pulse,
                                     float glucose
        ) {
}
