package org.ppke.itk.groupmealplanner.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ppke.itk.groupmealplanner.domain.Ingredient;
import org.ppke.itk.groupmealplanner.repository.IngredientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    @Operation(summary = "Get all ingredients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingredients found"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Ingredients not found")
    })
    @GetMapping("/ingredients")
    public List<Ingredient> getIngredients(@RequestParam(required = false, defaultValue = "100") Integer limit,
                                           @RequestParam(required = false, defaultValue = "desc") String sort) {
        if (!sort.equals("asc") && !sort.equals("desc")) {
            throw new IllegalArgumentException("Invalid sort parameter");
        }
        var sortParam = sort.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, "name") : Sort.by(Sort.Direction.DESC, "name");

        Page<Ingredient> ingredients = ingredientRepository.findAll(PageRequest.of(0, limit, sortParam));

        return ingredients.toList();
    }

    @GetMapping(value = "/ingredients/{id}", produces = APPLICATION_JSON_VALUE)
    public Optional<Ingredient> getIngredientById(@PathVariable Integer id) {
        log.info("Calling GET /ingredients endpoint");
        return ingredientRepository.findById(id);
    }

}
