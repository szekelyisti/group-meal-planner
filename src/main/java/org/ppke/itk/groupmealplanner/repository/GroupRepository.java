package org.ppke.itk.groupmealplanner.repository;

import org.ppke.itk.groupmealplanner.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findAll();

    List<Group> findAll(Integer limit, String sort);

    Optional<Group> findById(Integer id);

    Group saveGroup(String email, String name, Integer numOfMembers, List<String> members);

    void deleteGroup(Integer id);

    Group updateGroup(Integer id, String email, String name, Integer numOfMembers, List<String> members);
}
