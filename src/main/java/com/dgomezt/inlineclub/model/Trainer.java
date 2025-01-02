package com.dgomezt.inlineclub.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Trainer {
    @Id private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String birthdate;
    private String entryDate;
    private String specialty;
    private String description;
    private String photo;
}