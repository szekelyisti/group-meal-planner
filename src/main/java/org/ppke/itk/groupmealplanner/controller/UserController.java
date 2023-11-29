package org.ppke.itk.groupmealplanner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ppke.itk.groupmealplanner.domain.User;
import org.ppke.itk.groupmealplanner.repository.CustomUserRepository;
import org.ppke.itk.groupmealplanner.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final CustomUserRepository customUserRepository;

    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(required = false, defaultValue = "100") Integer limit,
                               @RequestParam(required = false, defaultValue = "desc") String sort) {
        log.info("Calling GET /users endpoint");
        var sortParam = sort.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, "name") : Sort.by(Sort.Direction.DESC, "name");
        return userRepository.findAll(PageRequest.of(0, limit, sortParam)).toList();
    }

    @GetMapping(value = "/users/{id}", produces = APPLICATION_JSON_VALUE)
    public Optional<User> getUserById(@PathVariable("id") Integer id) {
        log.info("Calling GET /users endpoint");
        return userRepository.findById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody String name, @RequestBody String email) {
        log.info("Calling POST /users endpoint");
        return customUserRepository.createUser(name, email);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") Integer id, @RequestBody(required = false) String name, @RequestBody(required = false) String email) {
        log.info("Calling PUT /users endpoint");
        return customUserRepository.updateUser(id, name, email);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        log.info("Calling DELETE /users endpoint");
        customUserRepository.deleteUser(id);
    }
}
