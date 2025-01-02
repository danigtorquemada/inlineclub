package com.dgomezt.inlineclub.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dgomezt.inlineclub.model.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
}