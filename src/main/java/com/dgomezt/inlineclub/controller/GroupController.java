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

import com.dgomezt.inlineclub.model.Group;
import com.dgomezt.inlineclub.service.GroupService;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    
    @GetMapping()
    public ResponseEntity<List<Group>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable String id) {
        Group group = groupService.getGroupById(id);
        if (group == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(group);
    }

    @PostMapping()
    public ResponseEntity<String> createGroup(@RequestBody Group group) {
        Group newGroup = groupService.createGroup(group);
        return ResponseEntity.ok(newGroup.getId());
    }

    @PatchMapping("{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable String id, @RequestBody Group group) {
        Group existingGroup = groupService.updateGroup(id, group);
        return ResponseEntity.ok(existingGroup);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable String id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok("");
    }
}
