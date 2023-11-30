package org.ppke.itk.groupmealplanner.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ppke.itk.groupmealplanner.domain.Group;
import org.ppke.itk.groupmealplanner.repository.CustomGroupRepository;
import org.ppke.itk.groupmealplanner.repository.GroupRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Group")
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepository groupRepository;
    private final CustomGroupRepository customGroupRepository;


    @GetMapping("/groups")
    public List<Group> getGroups(@RequestParam(required = false, defaultValue = "100") Integer limit,
                                 @RequestParam(required = false, defaultValue = "desc") String sort) {
        var sortParam = sort.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, "name") : Sort.by(Sort.Direction.DESC, "name");
        return groupRepository.findAll(PageRequest.of(0, limit, sortParam)).toList();
    }

    @GetMapping(value = "/groups/{id}", produces = APPLICATION_JSON_VALUE)
    public Optional<Group> getGroupById(@PathVariable("id") Integer id) {
        return groupRepository.findById(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/groups")
    public void createGroup(@RequestBody Group group) {
        groupRepository.saveAndFlush(group);
    }

    @PutMapping("/groups/{id}")
    public Group updateGroup(@PathVariable("id") Integer id, @RequestBody(required = false) Group groupUpdate) {
        return customGroupRepository.updateGroup(id, groupUpdate.getOwner().getUsername(), groupUpdate.getName(), groupUpdate.getNumOfMembers(), groupUpdate.getMembers());
    }

    @DeleteMapping("/groups/{id}")
    public void deleteGroup(@PathVariable("id") Integer id) {
        groupRepository.deleteById(id);
    }
}
