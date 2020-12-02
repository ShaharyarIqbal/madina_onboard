package com.example.onboard.domain.model;

import com.example.onboard.domain.model.security.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PrayerTime extends BaseModel {


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="client_id",nullable = false)
    private Client client;

    LocalDate date;


    LocalTime fajr_adhaan;


    LocalTime fajr_iqamah;



    LocalTime dhuhr_adhaan;


    LocalTime dhuhr_iqamah;


    LocalTime asr_adhaan;


    LocalTime asr_iqamah;


    LocalTime maghrib_adhaan;

    LocalTime maghrib_iqamah;


    LocalTime isha_adhaan;


    LocalTime isha_iqamah;
}
