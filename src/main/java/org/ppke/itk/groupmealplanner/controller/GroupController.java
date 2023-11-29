package org.ppke.itk.groupmealplanner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ppke.itk.groupmealplanner.domain.Group;
import org.ppke.itk.groupmealplanner.repository.CustomGroupRepository;
import org.ppke.itk.groupmealplanner.repository.GroupRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepository groupRepository;
    private final CustomGroupRepository customGroupRepository;


    @GetMapping(value = "/groups/{id}", produces = APPLICATION_JSON_VALUE)
    public Optional<Group> getGroupById(@PathVariable("id") Integer id) {
        return groupRepository.findById(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/groups")
    public Group createGroup(@RequestBody String email, @RequestBody String name, @RequestBody Integer numOfMembers, @RequestBody List<String> members) {
        return customGroupRepository.createGroup(email, name, numOfMembers, members.toString());
    }

    @PutMapping("/groups/{id}")
    public Group updateGroup(@PathVariable("id") Integer id, @RequestBody(required = false) String email, @RequestBody(required = false) String name, @RequestBody(required = false) Integer numOfMembers, @RequestBody(required = false) List<String> members) {
        return customGroupRepository.updateGroup(id, email, name, numOfMembers, members);
    }

    @DeleteMapping("/groups/{id}")
    public void deleteGroup(@PathVariable("id") Integer id) {
        customGroupRepository.deleteGroup(id);
    }
}
