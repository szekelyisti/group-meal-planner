package org.ppke.itk.groupmealplanner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ppke.itk.groupmealplanner.domain.User;
import org.ppke.itk.groupmealplanner.repository.mocks.MockUserRepository;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final MockUserRepository userRepository;

    @GetMapping("/users")
    public String getUsers(@RequestParam(required = false, defaultValue = "100") Integer limit,
                           @RequestParam(required = false, defaultValue = "desc") String sort) {
        log.info("Calling GET /users endpoint");
        return userRepository.findAll(limit, sort).toString();
    }

    @GetMapping(value = "/users/{id}", produces = APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable("id") Integer id) {
        log.info("Calling GET /users endpoint");
        return userRepository.findById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody String name, @RequestBody String email) {
        log.info("Calling POST /users endpoint");
        return userRepository.saveUser(name, email);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") Integer id, @RequestBody(required = false) String name, @RequestBody(required = false) String email) {
        log.info("Calling PUT /users endpoint");
        return userRepository.updateUser(id, name, email);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        log.info("Calling DELETE /users endpoint");
        userRepository.deleteUser(id);
    }
}
