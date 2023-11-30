package org.ppke.itk.groupmealplanner.repository;

import org.ppke.itk.groupmealplanner.domain.Group;

public interface CustomGroupRepository {

    Group updateGroup(Integer id, String email, String name, Integer numOfMembers, String members);

}
