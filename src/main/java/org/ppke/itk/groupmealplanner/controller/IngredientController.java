package org.ppke.itk.groupmealplanner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ppke.itk.groupmealplanner.domain.Ingredient;
import org.ppke.itk.groupmealplanner.repository.mocks.MockIngredientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class IngredientController {

    private final MockIngredientRepository ingredientRepository;

    @GetMapping("/ingredients")
    public List<Ingredient> getIngredients(@RequestParam(required = false, defaultValue = "100") Integer limit,
                                           @RequestParam(required = false, defaultValue = "desc") String sort) {
        log.info("Calling GET /ingredients endpoint");
        return ingredientRepository.findAll(limit, sort);
    }

    @GetMapping("/ingredients/{id}", produces = APPLICATION_JSON_VALUE)
    public Ingredient getIngredientById(@PathVariable Integer id) {
        log.info("Calling GET /ingredients endpoint");
        return ingredientRepository.findById(id);
    }

}
