package org.ppke.itk.groupmealplanner.repository;

import org.ppke.itk.groupmealplanner.domain.Group;

import java.util.List;

public interface CustomGroupRepository {

    Group createGroup(String email, String name, Integer numOfMembers, String members);

    void deleteGroup(Integer id);


    Group updateGroup(Integer id, String email, String name, Integer numOfMembers, List<String> members);

}
