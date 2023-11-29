package org.ppke.itk.groupmealplanner.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ppke.itk.groupmealplanner.domain.Group;
import org.ppke.itk.groupmealplanner.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomGroupRepositoryImpl implements CustomGroupRepository{

    @PersistenceContext
    private EntityManager entityManager;

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Group createGroup(String email, String name, Integer numOfMembers, String members) {
        Group group;

        Optional<User> existingOwner = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .setMaxResults(1).getResultStream().findFirst();
        if (existingOwner.isEmpty()) {
            throw new NoSuchElementException(String.format("No user found for email %s", email));
        }

        Optional<Group> existingGroup = entityManager.createQuery("SELECT g FROM Group g WHERE g.name = :name and g.owner = :ownerId", Group.class)
                .setParameter("name", name)
                .setParameter("ownerId", existingOwner.get().getId())
                .setMaxResults(1).getResultStream().findFirst();

        if (existingGroup.isPresent()) {
            group = existingGroup.get();
            group.setNumOfMembers(numOfMembers);
            group.setMembers(members);
            groupRepository.saveAndFlush(group);
        } else {
            group = new Group();
            group.setOwner(existingOwner.get());
            group.setName(name);
            group.setNumOfMembers(numOfMembers);
            group.setMembers(members);
            entityManager.persist(group);
        }

        return group;

    }

    @Override
    @Transactional
    public void deleteGroup(Integer id) {
        Optional<Group> existingGroup = groupRepository.findById(id);

        if (existingGroup.isPresent()) {
            entityManager.remove(existingGroup.get());
        }
    }

    @Override
    @Transactional
    public Group updateGroup(Integer id, String email, String name, Integer numOfMembers, List<String> members) {
        Optional<Group> existingGroup = groupRepository.findById(id);

        if (existingGroup.isPresent()) {
            Group group = existingGroup.get();
            group.setOwner(userRepository.findByEmail(email).get());
            group.setName(name);
            group.setNumOfMembers(numOfMembers);
            group.setMembers(members.toString());
            groupRepository.saveAndFlush(group);
            return group;
        }
        return null;
    }
}
