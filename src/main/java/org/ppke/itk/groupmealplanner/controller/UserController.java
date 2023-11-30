package org.ppke.itk.groupmealplanner.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "User")
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
    public void createUser(@RequestBody User user) {
        userRepository.saveAndFlush(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") Integer id, @RequestBody(required = false) User userUpdate) {
        return customUserRepository.updateUser(id, userUpdate);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
    }
}
