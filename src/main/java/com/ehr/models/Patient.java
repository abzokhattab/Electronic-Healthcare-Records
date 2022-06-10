package com.ehr.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Patient {
    String name;
    int age;
    float weight;
    float height;
    String sex;
    int bloodPressure;
    int pulse;
    float glucose;


}
