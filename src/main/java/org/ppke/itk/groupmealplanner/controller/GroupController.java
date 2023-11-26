package org.ppke.itk.groupmealplanner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ppke.itk.groupmealplanner.domain.Group;
import org.ppke.itk.groupmealplanner.repository.mocks.MockGroupRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final MockGroupRepository groupRepository;

    @GetMapping("/groups")
    public List<Group> getGroups(@RequestParam(required = false, defaultValue = "100") Integer limit,
                                 @RequestParam(required = false, defaultValue = "desc") String sort) {
        log.info("Calling GET /groups endpoint");
        return groupRepository.findAll(limit, sort);
    }

    @GetMapping(value = "/groups/{id}", produces = APPLICATION_JSON_VALUE)
    public Group getGroupById(@PathVariable("id") Integer id) {
        log.info("Calling GET /groups endpoint");
        return groupRepository.findById(id);
    }

    @PostMapping("/groups")
    public Group createGroup(@RequestBody String email, @RequestBody String name, @RequestBody Integer numOfMembers, @RequestBody List<String> members) {
        log.info("Calling POST /groups endpoint");
        return groupRepository.saveGroup(email, name, numOfMembers, members);
    }

    @PutMapping("/groups/{id}")
    public Group updateGroup(@PathVariable("id") Integer id, @RequestBody(required = false) String email, @RequestBody(required = false) String name, @RequestBody(required = false) Integer numOfMembers, @RequestBody(required = false) List<String> members) {
        log.info("Calling PUT /groups endpoint");
        return groupRepository.updateGroup(id, email, name, numOfMembers, members);
    }

    @DeleteMapping("/groups/{id}")
    public void deleteGroup(@PathVariable("id") Integer id) {
        log.info("Calling DELETE /groups endpoint");
        groupRepository.deleteGroup(id);
    }
}
