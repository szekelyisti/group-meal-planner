package org.ppke.itk.groupmealplanner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealsIngredientLink {

        private Integer id;
        private Integer mealId;
        private Integer ingredientId;
        private Integer quantity;
}
