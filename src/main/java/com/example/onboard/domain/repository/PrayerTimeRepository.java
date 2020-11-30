package com.example.onboard.domain.repository;

import com.example.onboard.domain.model.PrayerTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PrayerTimeRepository extends JpaRepository<PrayerTime,Long> {
    @Query(value = "select * from prayer_time where client_id=:id",nativeQuery = true)
    public Optional<PrayerTime> getPrayerTimeByClientId(@Param("id") Long id);
}
