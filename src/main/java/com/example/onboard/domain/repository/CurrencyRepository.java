package com.example.onboard.domain.repository;

import com.example.onboard.domain.model.Currency;
import com.example.onboard.domain.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Long> {
}
