package com.dgomezt.inlineclub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgomezt.inlineclub.model.Trick;
import com.dgomezt.inlineclub.service.TrickService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tricks")
public class TrickController {
    private TrickService trickService;

    public TrickController(TrickService trickService) {
        this.trickService = trickService;
    }

    @GetMapping()
    public ResponseEntity<List<Trick>> getAllTricks() {
        return ResponseEntity.ok(trickService.getAllTricks());
    }

    @GetMapping("{id}")
    public ResponseEntity<Trick> getTrickById(@PathVariable String id) {
        Trick trick = trickService.getTrickById(id);
        if (trick == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(trick);
    }

    @PostMapping()
    public ResponseEntity<String> createTrick(@RequestBody Trick trick) {
        Trick newTrick = trickService.createTrick(trick);
        return ResponseEntity.ok(newTrick.getId());
    }
    
    @PatchMapping("{id}")
    public ResponseEntity<Trick> updateTrick(@PathVariable String id, @RequestBody Trick trick) {
        Trick existingTrick = trickService.updateTrick(id, trick);
        return ResponseEntity.ok(existingTrick);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTrick(@PathVariable String id) {
        trickService.deleteTrick(id);
        return ResponseEntity.ok("");
    }
}