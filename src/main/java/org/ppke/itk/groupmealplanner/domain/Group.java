package org.ppke.itk.groupmealplanner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    private Integer id;
    private User owner;
    private String name;
    private Integer numOfMembers;
    private List<String> members;
}
