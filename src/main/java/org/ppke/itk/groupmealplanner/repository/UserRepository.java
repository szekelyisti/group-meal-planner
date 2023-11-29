package org.ppke.itk.groupmealplanner.repository;

import org.ppke.itk.groupmealplanner.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    List<User> findAll(Integer limit, String sort);

    Optional<User> findById(Integer id);

    User saveUser(String name, String email);

    void deleteUser(Integer id);

    User updateUser(Integer id, String name, String email);
}
