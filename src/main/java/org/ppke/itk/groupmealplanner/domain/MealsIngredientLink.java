package org.ppke.itk.groupmealplanner.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meals_ingredients_link")
public class MealsIngredientLink {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private Integer mealId;
        private Integer ingredientId;
        private Integer quantity;
}
