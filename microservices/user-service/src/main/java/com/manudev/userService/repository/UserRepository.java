package com.manudev.userService.repository;

import com.manudev.userService.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT o FROM Order o WHERE o.active = true")
    List<UserEntity> findAllActiveUsers();

    Optional<UserEntity> findByEmail(String email);
}
