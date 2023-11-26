package org.ppke.itk.groupmealplanner.repository;

import org.ppke.itk.groupmealplanner.domain.Ingredient;

import java.util.List;

public interface IngredientRepository {

    List<Ingredient> findAll();

    List<Ingredient> findAll(Integer limit, String sort);

    Ingredient findById(Integer id);
}
