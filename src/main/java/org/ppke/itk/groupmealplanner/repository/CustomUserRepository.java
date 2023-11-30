package org.ppke.itk.groupmealplanner.repository;

import org.ppke.itk.groupmealplanner.domain.User;

public interface CustomUserRepository {

    User updateUser(Integer id, User userUpdate);

}
