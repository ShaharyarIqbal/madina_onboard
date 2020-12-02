package com.example.onboard.service;

import com.example.onboard.domain.dto.PrayerTimeDto;
import com.example.onboard.domain.model.PrayerTime;

import java.util.List;

public interface PrayerTimeService {

    List<PrayerTime> getPrayerTimeByClientId(Long id);

    PrayerTime createPrayerTime (PrayerTimeDto prayerTimeDto);

    PrayerTime updatePrayerTime (Long id ,PrayerTimeDto prayerTimeDto);

    String deletePrayerTime (Long id);
}
