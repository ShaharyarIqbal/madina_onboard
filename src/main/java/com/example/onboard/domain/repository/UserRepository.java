package com.example.onboard.domain.repository;

import com.example.onboard.domain.model.security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUserNameOrEmail(String userName, String email);
}
