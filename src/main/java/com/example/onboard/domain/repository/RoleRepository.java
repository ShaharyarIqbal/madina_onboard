package com.example.onboard.domain.repository;

import com.example.onboard.constant.RoleName;
import com.example.onboard.domain.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Set<Role> findByName(RoleName name);
}