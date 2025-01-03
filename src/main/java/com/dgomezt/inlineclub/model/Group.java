package com.dgomezt.inlineclub.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

@Data
public class Group {
    @Id private String id;
    @DBRef(lazy = true) private Trainer trainer;
    @DBRef(lazy = true) private List<Student> students;
}
