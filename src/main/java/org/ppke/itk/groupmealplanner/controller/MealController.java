package org.ppke.itk.groupmealplanner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ppke.itk.groupmealplanner.domain.Meal;
import org.ppke.itk.groupmealplanner.repository.mocks.MockMealRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MealController {

    private final MockMealRepository mealRepository;

    @GetMapping("/meals")
    public List<Meal> getMeals(@RequestParam(required = false, defaultValue = "100") Integer limit,
                               @RequestParam(required = false, defaultValue = "desc") String sort) {
        log.info("Calling GET /meals endpoint");
        return mealRepository.findAll(limit, sort);
    }

    @GetMapping(value = "/meals/{id}", produces = APPLICATION_JSON_VALUE)
    public Meal getMealById(@PathVariable("id") Integer id) {
        log.info("Calling GET /meals/{} endpoint", id);
        return mealRepository.findById(id);
    }

    @PostMapping("/meals")
    public Meal createMeal(@RequestBody String name, @RequestBody String instructions, @RequestBody Integer approximatedPrice) {
        log.info("Calling POST /meals endpoint");
        return mealRepository.saveMeal(name, instructions, approximatedPrice);
    }

    @PutMapping("/meals/{id}")
    public Meal updateMeal(@PathVariable("id") Integer id, @RequestBody(required = false) String name, @RequestBody(required = false) String instructions, @RequestBody(required = false) Integer approximatedPrice) {
        log.info("Calling PUT /meals endpoint");
        return mealRepository.updateMeal(id, name, instructions, approximatedPrice);
    }

    @DeleteMapping("/meals/{id}")
    public void deleteMeal(@PathVariable("id") Integer id) {
        log.info("Calling DELETE /meals endpoint");
        mealRepository.deleteMeal(id);
    }
}
