package com.example.onboard.domain.repository;

import com.example.onboard.domain.model.ClientSetting;
import com.example.onboard.domain.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientSettingRepository extends JpaRepository<ClientSetting,Long> {

//    @Query(value = "select * from client_setting where client_id=:id ",nativeQuery = true)
    public Optional<ClientSetting> findByClientId(Long id);

}
