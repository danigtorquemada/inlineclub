package com.dgomezt.inlineclub.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.dgomezt.inlineclub.model.Trainer;

public interface TrainerRepository extends MongoRepository<Trainer, String> {
}
