package com.justin.springbootcrud.controller;

import com.justin.springbootcrud.model.Group;
import com.justin.springbootcrud.model.GroupRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class GroupController {
    private final Logger log = LoggerFactory.getLogger(GroupController.class);
    private GroupRepository groupRepository;
    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/groups")
    Collection<Group> getAllGroups() {
        return groupRepository.findAll();
    }
    @GetMapping("/group/{id}")
    ResponseEntity<Group> getGroup(@PathVariable long id) {
        Optional<Group> group = groupRepository.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/group")
    ResponseEntity<Group> createGroup(@Valid @RequestBody Group group) throws URISyntaxException {
        log.info("Request to create a new group: {}", group);
        Group result = groupRepository.save(group);
        return ResponseEntity.created(new URI("/api/group/" + result.getId())).body(result);
    }

    @PutMapping("/group/{id}")
    ResponseEntity<Group> updateGroup(@PathVariable long id, @Valid @RequestBody Group group) {
        log.info("Request to update a group: id = {} group = {}",id, group);
        if (groupRepository.existsById(id)) {
            group.setId(id);
            Group result = groupRepository.save(group);
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/group/{id}")
    ResponseEntity<?> deleteGroup(@PathVariable long id) {
        log.info("Request to delete a group: {}", id);
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
