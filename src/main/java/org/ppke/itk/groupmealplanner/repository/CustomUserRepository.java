package org.ppke.itk.groupmealplanner.repository;

import org.ppke.itk.groupmealplanner.domain.User;

public interface CustomUserRepository {

    User createUser(String name, String email);

    void deleteUser(Integer id);

    User updateUser(Integer id, String name, String email);

}
