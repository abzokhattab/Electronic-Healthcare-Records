package com.ehr.models;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Visit {

    int bloodPressure;
    int pulse;
    float glucose;
    String reason;
    String diagnosis;
    String prescription;


    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}

