package org.ppke.itk.groupmealplanner.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups_meals_link")
public class GroupsMealLink {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private Integer groupId;
        private Integer mealId;
        private Integer approximatedPrice;
        private String members;
}
