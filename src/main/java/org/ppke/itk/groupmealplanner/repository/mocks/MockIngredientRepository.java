package org.ppke.itk.groupmealplanner.repository.mocks;

import lombok.RequiredArgsConstructor;
import org.ppke.itk.groupmealplanner.domain.Ingredient;
import org.ppke.itk.groupmealplanner.repository.IngredientRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MockIngredientRepository implements IngredientRepository {

    private final static List<Ingredient> INGREDIENTS = List.of(
            new Ingredient(1, "Alma", "kg", 100),
            new Ingredient(2, "Körte", "kg", 200),
            new Ingredient(3, "Banán", "kg", 300),
            new Ingredient(4, "Szilva", "kg", 400),
            new Ingredient(5, "Barack", "kg", 500),
            new Ingredient(6, "Málna", "kg", 600),
            new Ingredient(7, "Eper", "kg", 700),
            new Ingredient(8, "Cseresznye", "kg", 800),
            new Ingredient(9, "Meggy", "kg", 900),
            new Ingredient(10, "Ribizli", "kg", 1000),
            new Ingredient(11, "Málna", "kg", 1100),
            new Ingredient(12, "Eper", "kg", 1200),
            new Ingredient(13, "Cseresznye", "kg", 1300),
            new Ingredient(14, "Meggy", "kg", 1400),
            new Ingredient(15, "Ribizli", "kg", 1500),
            new Ingredient(16, "Málna", "kg", 1600)
    );

    @Override
    public List<Ingredient> findAll() {
        return INGREDIENTS;
    }

    @Override
    public List<Ingredient> findAll(Integer limit, String sort) {
        if("asc".equalsIgnoreCase(sort)) {
            return INGREDIENTS.stream()
                    .sorted(Comparator.comparing(Ingredient::getApproximatedPrice))
                    .limit(limit)
                    .toList();
        } else if ("desc".equalsIgnoreCase(sort)) {
            return INGREDIENTS.stream()
                    .sorted(Comparator.comparing(Ingredient::getApproximatedPrice).reversed())
                    .limit(limit)
                    .toList();
        } else {
            throw new IllegalArgumentException("Invalid sort parameter: " + sort);
        }
    }

    @Override
    public Ingredient findById(Integer id) {
        return INGREDIENTS.stream()
                .filter(ingredient -> ingredient.getId().equals(id))
                .findFirst()
                .get();
    }

}
