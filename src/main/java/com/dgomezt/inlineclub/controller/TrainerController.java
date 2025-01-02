package com.dgomezt.inlineclub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgomezt.inlineclub.model.Trainer;
import com.dgomezt.inlineclub.service.TrainerService;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    private TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping()
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        return ResponseEntity.ok(trainerService.getAllTrainers());
    }

    @GetMapping("{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable String id) {
        Trainer trainer = trainerService.getTrainerById(id);
        if (trainer == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(trainer);
    }

    @PostMapping()
    public ResponseEntity<String> createTrainer(@RequestBody Trainer trainer) {
        Trainer newTrainer = trainerService.createTrainer(trainer);
        return ResponseEntity.ok(newTrainer.getId());
    }

    @PatchMapping("{id}")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable String id, @RequestBody Trainer trainer) {
        Trainer existingTrainer = trainerService.updateTrainer(id, trainer);
        return ResponseEntity.ok(existingTrainer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTrainer(@PathVariable String id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.ok("");
    }
}
