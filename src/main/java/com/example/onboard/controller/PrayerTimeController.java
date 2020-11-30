package com.example.onboard.controller;

import com.example.onboard.domain.dto.PrayerTimeDto;
import com.example.onboard.domain.model.PrayerTime;
import com.example.onboard.service.PrayerTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/prayertime", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrayerTimeController {

    @Autowired
    PrayerTimeService prayerTimeService;


    @GetMapping("/{id}")
    public PrayerTime getPrayerTimingByClientId(@Param("id") Long id)
    {
      return prayerTimeService.getPrayerTimeByClientId(id);
    }

    @PostMapping("/")
    public PrayerTime createPrayerTime(@RequestBody PrayerTimeDto  prayerTimeDto)
    {
        return prayerTimeService.createPrayerTime(prayerTimeDto);
    }

    @PutMapping ("/{id}")
    public PrayerTime updatePrayerTime(@Param("id") Long id,@RequestBody PrayerTimeDto  prayerTimeDto )
    {
        return prayerTimeService.updatePrayerTime(id,prayerTimeDto);
    }
    @DeleteMapping("/{id}")
    public String deletePrayerTime(@Param("id") Long id)
    {
        return prayerTimeService.deletePrayerTime(id);
    }
}
