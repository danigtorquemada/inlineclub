package com.dgomezt.inlineclub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dgomezt.inlineclub.model.Student;
import com.dgomezt.inlineclub.repository.StudentRepository;
import com.dgomezt.inlineclub.utils.NullUtils;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(String id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent == null) {
            return null;
        }

        NullUtils.updateIfPresent(existingStudent::setFirstname, student.getFirstname());
        NullUtils.updateIfPresent(existingStudent::setLastname, student.getLastname());
        NullUtils.updateIfPresent(existingStudent::setEmail, student.getEmail());
        NullUtils.updateIfPresent(existingStudent::setPhone, student.getPhone());
        NullUtils.updateIfPresent(existingStudent::setBirthdate, student.getBirthdate());
        NullUtils.updateIfPresent(existingStudent::setEntryDate, student.getEntryDate());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
