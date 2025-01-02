package com.dgomezt.inlineclub.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Student {
    @Id private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private Date birthdate;
    private String entryDate;
}