package com.ehr.models;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder

public class Patient {
    String id  ;
    String name;
    int age;
    float weight;
    float height;
    String sex;
    int bloodPressure;
    int pulse;
    float glucose;


    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}
