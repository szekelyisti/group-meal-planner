package org.ppke.itk.groupmealplanner.repository;

import org.ppke.itk.groupmealplanner.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<User> findAll();

    List<User> findAll(Integer limit, String sort);

    User findById(Integer id);

    User saveUser(String name, String email);

    void deleteUser(Integer id);

    User updateUser(Integer id, String name, String email);
}
