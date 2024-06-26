package com.apiTPO.technologyHouse.app.repositories;

import com.apiTPO.technologyHouse.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String mail);
}
