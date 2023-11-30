package org.ppke.itk.groupmealplanner.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ppke.itk.groupmealplanner.domain.Group;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomGroupRepositoryImpl implements CustomGroupRepository{

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Group updateGroup(Integer id, String username, String name, Integer numOfMembers, String members) {
        Optional<Group> existingGroup = groupRepository.findById(id);

        if (existingGroup.isPresent()) {
            Group group = existingGroup.get();
            group.setOwner(userRepository.findByUsername(username).get());
            group.setName(name);
            group.setNumOfMembers(numOfMembers);
            group.setMembers(members);
            groupRepository.saveAndFlush(group);
            return group;
        }
        return null;
    }
}
