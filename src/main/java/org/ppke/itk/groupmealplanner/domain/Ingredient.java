package org.ppke.itk.groupmealplanner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private Integer id;
    private String name;
    private String unit;
    private Integer approximatedPrice;
}
