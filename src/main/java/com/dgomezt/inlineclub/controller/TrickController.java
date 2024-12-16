package com.dgomezt.inlineclub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgomezt.inlineclub.model.Trick;
import com.dgomezt.inlineclub.repository.TrickRepository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tricks")
public class TrickController {
    TrickRepository trickRepository;

    public TrickController(TrickRepository trickRepository) {
        this.trickRepository = trickRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Trick>> getAllTricks() {
        return ResponseEntity.ok(trickRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Trick> getTrickById(@RequestParam String id) {
        Trick trick = trickRepository.findById(id).orElse(null);
        if (trick == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(trick);
    }

    @PostMapping()
    public ResponseEntity<String> createTrick(@RequestBody Trick trick) {
        Trick newTrick = trickRepository.save(trick);
        return ResponseEntity.ok(newTrick.getId());
    }
    
    @PatchMapping("{id}")
    public ResponseEntity<Trick> updateTrick(@RequestParam String id, @RequestBody Trick trick) {
        Trick existingTrick = trickRepository.findById(id).orElse(null);
        if (existingTrick == null) {
            return ResponseEntity.notFound().build();
        }

        existingTrick.setName(trick.getName());
        existingTrick.setDescription(trick.getDescription());
        existingTrick.setDifficulty(trick.getDifficulty());
        existingTrick.setFamily(trick.getFamily());
        existingTrick.setVideoUrl(trick.getVideoUrl());

        trickRepository.save(existingTrick);
        return ResponseEntity.ok(existingTrick);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTrick(@RequestParam String id) {
        Trick existingTrick = trickRepository.findById(id).orElse(null);
        if (existingTrick == null) {
            return ResponseEntity.notFound().build();
        }

        trickRepository.delete(existingTrick);
        return ResponseEntity.ok(existingTrick.getId());
    }
}