package org.ppke.itk.groupmealplanner.repository;

import org.ppke.itk.groupmealplanner.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findAll();

    List<Meal> findAll(Integer limit, String sort);

    Optional<Meal> findById(Integer id);


    Meal saveMeal(String name, String instructions, Integer approximatedPrice);

    void deleteMeal(Integer id);

    Meal updateMeal(Integer id, String name, String instructions, Integer approximatedPrice);
}
