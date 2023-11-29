package org.ppke.itk.groupmealplanner.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ppke.itk.groupmealplanner.domain.User;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(String name, String email) {
        User user;

        Optional<User> existingUser = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .setMaxResults(1).getResultStream().findFirst();

        if (existingUser.isPresent()) {
            throw new NoSuchElementException(String.format("User already exists for email %s", email));
        } else {
            user = new User();
            user.setName(name);
            user.setEmail(email);
            entityManager.persist(user);
        }

        return user;
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            entityManager.remove(existingUser.get());
        }
    }

    @Override
    @Transactional
    public User updateUser(Integer id, String name, String email) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(name);
            user.setEmail(email);
            userRepository.saveAndFlush(user);
            return user;
        } else {
            throw new NoSuchElementException(String.format("No user found for id %s", id));
        }

    }
}
