package org.ppke.itk.groupmealplanner.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.ppke.itk.groupmealplanner.domain.Meal;
import org.ppke.itk.groupmealplanner.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomMealRepositoryImpl implements CustomMealRepository{

    @PersistenceContext
    private EntityManager entityManager;

    private final MealRepository mealRepository;

    @Override
    public Meal createMeal(String name, String instructions, Integer approximatedPrice) {
        Meal meal = new Meal();
        meal.setName(name);
        meal.setInstructions(instructions);
        meal.setApproximatedPrice(approximatedPrice);
        entityManager.persist(meal);
        return meal;
    }

    @Override
    public void deleteMeal(Integer id) {
        Optional<Meal> existingMeal = mealRepository.findById(id);

        if (existingMeal.isPresent()) {
            entityManager.remove(existingMeal.get());
        }
    }

    @Override
    public Meal updateMeal(Integer id, String name, String instructions, Integer approximatedPrice) {
        Optional<Meal> existingMeal = mealRepository.findById(id);

        if (existingMeal.isPresent()) {
            Meal meal = existingMeal.get();
            meal.setName(name);
            meal.setInstructions(instructions);
            meal.setApproximatedPrice(approximatedPrice);
            entityManager.persist(meal);
            return meal;
        }
        return null;
    }
}
