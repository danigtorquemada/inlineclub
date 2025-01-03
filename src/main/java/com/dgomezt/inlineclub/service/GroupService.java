package com.dgomezt.inlineclub.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dgomezt.inlineclub.model.Group;
import com.dgomezt.inlineclub.model.Student;
import com.dgomezt.inlineclub.model.Trainer;
import com.dgomezt.inlineclub.repository.GroupRepository;
import com.dgomezt.inlineclub.repository.StudentRepository;
import com.dgomezt.inlineclub.repository.TrainerRepository;
import com.dgomezt.inlineclub.utils.NullUtils;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private TrainerRepository trainerRepository;
    private StudentRepository studentRepository;

    public GroupService(GroupRepository groupRepository,
                        TrainerRepository trainerRepository,
                        StudentRepository studentRepository) {
        this.groupRepository = groupRepository;
        this.trainerRepository = trainerRepository;
        this.studentRepository = studentRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(String id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Group createGroup(Group group) {
        if (group.getTrainer() != null) {
            group.setTrainer(checkTrainerSaved(group.getTrainer()));
        }

        if (group.getStudents() != null && !group.getStudents().isEmpty()) {
            group.setStudents(checkStudentsSaved(group.getStudents()));
        }

        return groupRepository.save(group);
    }

    public Group updateGroup(String id, Group group) {
        Group existingGroup = groupRepository.findById(id).orElse(null);
        if (existingGroup == null) {
            return null;
        }

        Optional<Trainer> trainer = Optional.ofNullable(group.getTrainer());
        trainer.ifPresent(t -> NullUtils.updateIfPresent(existingGroup::setTrainer, checkTrainerSaved(t)));

        Optional<List<Student>> students = Optional.ofNullable(group.getStudents());
        students.ifPresent(s -> {
            if (!s.isEmpty()) {
                NullUtils.updateIfPresent(existingGroup::setStudents, checkStudentsSaved(s));
            }
        });

        return groupRepository.save(existingGroup);
    }

    public void deleteGroup(String id) {
        groupRepository.deleteById(id);
    }

    private Trainer checkTrainerSaved(Trainer trainer) {
        if (trainer.getId() == null) {
            return trainerRepository.save(trainer);
        } else {
            Optional<Trainer> existingTrainer = trainerRepository.findById(trainer.getId());
            if (existingTrainer.isPresent()) {
                return existingTrainer.get();
            } else {
                return trainerRepository.save(trainer);
            }
        }
    }

    private List<Student> checkStudentsSaved(List<Student> students) {
        return students.stream().map(student -> {
            if (student.getId() == null) {
                return studentRepository.save(student);
            } else {
                Optional<Student> existingStudent = studentRepository.findById(student.getId());
                if (existingStudent.isPresent()) {
                    return existingStudent.get();
                } else {
                    return studentRepository.save(student);
                }
            }
        }).collect(Collectors.toList());
    }
}
