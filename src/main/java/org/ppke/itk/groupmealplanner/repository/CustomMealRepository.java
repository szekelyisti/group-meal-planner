package org.ppke.itk.groupmealplanner.repository;

import org.ppke.itk.groupmealplanner.domain.Meal;

public interface CustomMealRepository {

    Meal updateMeal(Integer id, String name, String instructions, Integer approximatedPrice);

}
