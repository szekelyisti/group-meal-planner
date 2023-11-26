package org.ppke.itk.groupmealplanner.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GroupsMealLink {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private Integer groupId;
        private Integer mealId;
        private Integer approximatedPrice;
        private String members;
}
