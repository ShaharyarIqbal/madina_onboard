package com.example.onboard.domain.repository;

import com.example.onboard.domain.model.security.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByUserName(String userName);
}
