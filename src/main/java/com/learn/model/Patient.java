package com.learn.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Patient {
    private String name;
    private String otherPatientDetails;
    private List<Practitioner> references = new ArrayList<>();
}
