package org.ppke.itk.groupmealplanner.repository;

import org.ppke.itk.groupmealplanner.domain.Meal;

import java.util.List;


public interface MealRepository {

    List<Meal> findAll();

    List<Meal> findAll(Integer limit, String sort);

    Meal findById(Integer id);


    Meal saveMeal(String name, String instructions, Integer approximatedPrice);

    void deleteMeal(Integer id);

    Meal updateMeal(Integer id, String name, String instructions, Integer approximatedPrice);
}
