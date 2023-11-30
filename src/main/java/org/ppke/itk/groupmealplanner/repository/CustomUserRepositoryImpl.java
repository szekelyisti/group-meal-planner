package org.ppke.itk.groupmealplanner.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ppke.itk.groupmealplanner.domain.User;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User updateUser(Integer id, User userUpdate) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(userUpdate.getName());
            user.setEmail(userUpdate.getEmail());
            userRepository.saveAndFlush(user);
            return user;
        } else {
            throw new NoSuchElementException(String.format("No user found for id %s", id));
        }

    }
}
