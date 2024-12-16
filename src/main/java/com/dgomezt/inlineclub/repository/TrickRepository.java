package com.dgomezt.inlineclub.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.dgomezt.inlineclub.model.Trick;

public interface TrickRepository  extends MongoRepository<Trick, String> {
    public List<Trick> findByFamily(String family);
    public List<Trick> findByDifficulty(String difficulty);
}
