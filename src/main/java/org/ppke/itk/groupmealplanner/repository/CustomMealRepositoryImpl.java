package org.ppke.itk.groupmealplanner.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ppke.itk.groupmealplanner.domain.Meal;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomMealRepositoryImpl implements CustomMealRepository{

    @PersistenceContext
    private EntityManager entityManager;

    private final MealRepository mealRepository;

    @Override
    @Transactional
    public Meal updateMeal(Integer id, String name, String instruction, Integer approximatedPrice) {
        Optional<Meal> existingMeal = mealRepository.findById(id);

        if (existingMeal.isPresent()) {
            Meal meal = existingMeal.get();
            meal.setName(name);
            meal.setInstruction(instruction);
            meal.setApproximatedPrice(approximatedPrice);
            entityManager.persist(meal);
            return meal;
        }
        return null;
    }
}
