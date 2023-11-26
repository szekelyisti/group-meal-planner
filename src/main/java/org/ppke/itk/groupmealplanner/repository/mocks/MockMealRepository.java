package org.ppke.itk.groupmealplanner.repository.mocks;

import lombok.RequiredArgsConstructor;
import org.ppke.itk.groupmealplanner.domain.Meal;
import org.ppke.itk.groupmealplanner.repository.MealRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MockMealRepository implements MealRepository {

    private List<Meal> MEALS = List.of(
            new Meal(1, "Pörkölt", "Főzd meg", 1000),
            new Meal(2, "Rántott hús", "Sütőben süsd meg", 1500),
            new Meal(3, "Túrós csusza", "Főzd meg", 800)
    );

    @Override
    public List<Meal> findAll() {
        return MEALS;
    }

    @Override
    public List<Meal> findAll(Integer limit, String sort) {
        if("asc".equalsIgnoreCase(sort)) {
            return MEALS.stream()
                    .sorted(Comparator.comparing(Meal::getApproximatedPrice))
                    .limit(limit)
                    .toList();
        } else if ("desc".equalsIgnoreCase(sort)) {
            return MEALS.stream()
                    .sorted(Comparator.comparing(Meal::getApproximatedPrice).reversed())
                    .limit(limit)
                    .toList();
        } else {
            throw new IllegalArgumentException("Invalid sort parameter: " + sort);
        }
    }

    @Override
    public Meal findById(Integer id) {
        return MEALS.stream()
                .filter(meal -> meal.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public Meal saveMeal(String name, String instructions, Integer approximatedPrice) {
        Meal meal = new Meal(MEALS.size() + 1, name, instructions, approximatedPrice);
        MEALS.add(meal);
        return meal;
    }

    @Override
    public Meal updateMeal(Integer id, String name, String instructions, Integer approximatedPrice) {
        Meal meal = findById(id);
        if (name != null) {
            meal.setName(name);
        }
        if (instructions != null) {
            meal.setInstructions(instructions);
        }
        if (approximatedPrice != null) {
            meal.setApproximatedPrice(approximatedPrice);
        }
        return meal;
    }

    @Override
    public void deleteMeal(Integer id) {
        MEALS.removeIf(meal -> meal.getId().equals(id));
    }

}
