package com.dgomezt.inlineclub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dgomezt.inlineclub.model.Trainer;
import com.dgomezt.inlineclub.repository.TrainerRepository;
import com.dgomezt.inlineclub.utils.NullUtils;

@Service
public class TrainerService {
    private TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public Trainer getTrainerById(String id) {
        return trainerRepository.findById(id).orElse(null);
    }

    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public Trainer updateTrainer(String id, Trainer trainer) {
        Trainer existingTrainer = trainerRepository.findById(id).orElse(null);
        if (existingTrainer == null) {
            return null;
        }

        NullUtils.updateIfPresent(existingTrainer::setFirstname, trainer.getFirstname());
        NullUtils.updateIfPresent(existingTrainer::setLastname, trainer.getLastname());
        NullUtils.updateIfPresent(existingTrainer::setEmail, trainer.getEmail());
        NullUtils.updateIfPresent(existingTrainer::setPhone, trainer.getPhone());
        NullUtils.updateIfPresent(existingTrainer::setBirthdate, trainer.getBirthdate());
        NullUtils.updateIfPresent(existingTrainer::setEntryDate, trainer.getEntryDate());
        NullUtils.updateIfPresent(existingTrainer::setSpecialty, trainer.getSpecialty());
        NullUtils.updateIfPresent(existingTrainer::setDescription, trainer.getDescription());
        NullUtils.updateIfPresent(existingTrainer::setPhoto, trainer.getPhoto());

        return trainerRepository.save(existingTrainer);
    }

    public void deleteTrainer(String id) {
        trainerRepository.deleteById(id);
    }
}