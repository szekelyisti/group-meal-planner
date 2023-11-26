package org.ppke.itk.groupmealplanner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meal {

    private Integer id;
    private String name;
    private String instructions;
    private Integer approximatedPrice;

}
