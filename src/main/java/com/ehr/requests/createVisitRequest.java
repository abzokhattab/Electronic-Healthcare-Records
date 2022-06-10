package com.ehr.requests;

public record createVisitRequest( int bloodPressure,
        int pulse,
        float glucose,
        String reason,
        String diagnosis,
        String prescription
) {
}
