package org.ppke.itk.groupmealplanner.repository;

import org.ppke.itk.groupmealplanner.domain.Group;

import java.util.List;

public interface GroupRepository {

    List<Group> findAll();

    List<Group> findAll(Integer limit, String sort);

    Group findById(Integer id);

    Group saveGroup(String email, String name, Integer numOfMembers, List<String> members);

    void deleteGroup(Integer id);

    Group updateGroup(Integer id, String email, String name, Integer numOfMembers, List<String> members);
}
