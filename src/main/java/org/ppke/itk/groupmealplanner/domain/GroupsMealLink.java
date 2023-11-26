package org.ppke.itk.groupmealplanner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupsMealLink {

        private Integer id;
        private Integer groupId;
        private Integer mealId;
        private Integer approximatedPrice;
        private List<String> members;
}
