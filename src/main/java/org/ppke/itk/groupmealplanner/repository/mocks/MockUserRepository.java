package org.ppke.itk.groupmealplanner.repository.mocks;

import lombok.RequiredArgsConstructor;
import org.ppke.itk.groupmealplanner.domain.User;
import org.ppke.itk.groupmealplanner.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MockUserRepository implements UserRepository {

    private List<User> USERS = List.of(
            new User(1, "Péter", "peter@email.com"),
            new User(2, "Géza", "geza@email.com"),
            new User(3, "Józsi", "jozsi@email.com"),
            new User(4, "Béla", "bela@email.com")
    );

    @Override
    public List<User> findAll() {
        return USERS;
    }

    @Override
    public List<User> findAll(Integer limit, String sort) {
        if("asc".equalsIgnoreCase(sort)) {
            return USERS.stream()
                    .sorted(Comparator.comparing(User::getName))
                    .limit(limit)
                    .toList();
        } else if ("desc".equalsIgnoreCase(sort)) {
            return USERS.stream()
                    .sorted(Comparator.comparing(User::getName).reversed())
                    .limit(limit)
                    .toList();
        } else {
            throw new IllegalArgumentException("Invalid sort parameter: " + sort);
        }
    }

    @Override
    public User findById(Integer id) {
        return USERS.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public User saveUser(String name, String email) {
        User user = new User(USERS.size() + 1, name, email);
        USERS.add(user);
        return user;
    }

    @Override
    public User updateUser(Integer id, String name, String email) {
        User user = findById(id);
        if(name != null) {
            user.setName(name);
        }
        if(email != null) {
            user.setEmail(email);
        }
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        USERS.removeIf(user -> user.getId().equals(id));
    }
}
