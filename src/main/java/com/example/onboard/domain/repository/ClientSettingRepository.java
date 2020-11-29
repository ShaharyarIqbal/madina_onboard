package com.example.onboard.domain.repository;

import com.example.onboard.domain.model.ClientSetting;
import com.example.onboard.domain.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientSettingRepository extends JpaRepository<ClientSetting,Long> {
}
