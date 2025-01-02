package com.dgomezt.inlineclub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dgomezt.inlineclub.model.Trick;
import com.dgomezt.inlineclub.repository.TrickRepository;
import com.dgomezt.inlineclub.utils.NullUtils;

@Service
public class TrickService {
    private TrickRepository trickRepository;

    public TrickService(TrickRepository trickRepository) {
        this.trickRepository = trickRepository;
    }

    public List<Trick> getAllTricks() {
        return trickRepository.findAll();
    }

    public Trick getTrickById(String id) {
        return trickRepository.findById(id).orElse(null);
    }

    public Trick createTrick(Trick trick) {
        return trickRepository.save(trick);
    }

    public Trick updateTrick(String id, Trick trick) {
        Trick existingTrick = trickRepository.findById(id).orElse(null);
        if (existingTrick == null) {
            return null;
        }

        NullUtils.updateIfPresent(existingTrick::setName, trick.getName());
        NullUtils.updateIfPresent(existingTrick::setDescription, trick.getDescription());
        NullUtils.updateIfPresent(existingTrick::setDifficulty, trick.getDifficulty());
        NullUtils.updateIfPresent(existingTrick::setFamily, trick.getFamily());
        NullUtils.updateIfPresent(existingTrick::setVideoUrl, trick.getVideoUrl());

        return trickRepository.save(existingTrick);
    }

    public void deleteTrick(String id) {
        trickRepository.deleteById(id);
    }
}
