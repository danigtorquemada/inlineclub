package com.dgomezt.inlineclub.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dgomezt.inlineclub.model.Group;

public interface GroupRepository extends MongoRepository<Group, String> {
}
