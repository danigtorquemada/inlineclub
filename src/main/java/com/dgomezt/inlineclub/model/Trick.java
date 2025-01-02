package com.dgomezt.inlineclub.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Trick {
    @Id private String id;
    private String name;
    private String description;
    private String difficulty;
    private String family;
    private String videoUrl; 
}