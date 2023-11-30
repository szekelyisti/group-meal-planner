package org.ppke.itk.groupmealplanner.repository;

import org.ppke.itk.groupmealplanner.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Page<User> findAll(Pageable page);

    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
