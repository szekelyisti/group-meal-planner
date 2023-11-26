package org.ppke.itk.groupmealplanner.repository.mocks;

import lombok.RequiredArgsConstructor;
import org.ppke.itk.groupmealplanner.domain.Group;
import org.ppke.itk.groupmealplanner.domain.User;
import org.ppke.itk.groupmealplanner.repository.GroupRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MockGroupRepository implements GroupRepository {

    private List<Group> GROUPS = List.of(
            new Group(1, new User(), "Kis csoport", 3, List.of("1", "2", "3")),
            new Group(2, new User(), "Nagy csoport", 5, List.of("1", "2", "3", "4", "5")),
            new Group(3, new User(), "Közepes csoport", 4, List.of("1", "2", "3", "4")),
            new Group(4, new User(), "Közepes csoport", 4, List.of("1", "2", "3", "4")),
            new Group(5, new User(), "Közepes csoport", 4, List.of("1", "2", "3", "4"))
    );
    @Override
    public List<Group> findAll() {
        return GROUPS;
    }

    @Override
    public List<Group> findAll(Integer limit, String sort) {
        if("asc".equalsIgnoreCase(sort)) {
            return GROUPS.stream()
                    .sorted(Comparator.comparing(Group::getId))
                    .limit(limit)
                    .toList();
        } else if ("desc".equalsIgnoreCase(sort)) {
            return GROUPS.stream()
                    .sorted(Comparator.comparing(Group::getId).reversed())
                    .limit(limit)
                    .toList();
        } else {
            throw new IllegalArgumentException("Invalid sort parameter: " + sort);
        }
    }

    @Override
    public Group findById(Integer id) {
        return GROUPS.stream()
                .filter(group -> group.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public Group saveGroup(String email, String name, Integer numOfMembers, List<String> members) {
        Group group = new Group();
        group.setOwner(new User());
        group.setName(name);
        group.setNumOfMembers(numOfMembers);
        group.setMembers(members);
        GROUPS.add(group);
        return group;
    }

    @Override
    public Group updateGroup(Integer id, String email, String name, Integer numOfMembers, List<String> members) {
        Group group = findById(id);
        if(name != null) {
            group.setName(name);
        }
        if(numOfMembers != null) {
            group.setNumOfMembers(numOfMembers);
        }
        if(members != null) {
            group.setMembers(members);
        }
        return group;
    }

    @Override
    public void deleteGroup(Integer id) {
        GROUPS.removeIf(group -> group.getId().equals(id));
    }
}
