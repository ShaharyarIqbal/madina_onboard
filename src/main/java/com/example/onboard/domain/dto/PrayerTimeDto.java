package com.example.onboard.domain.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrayerTimeDto {


    Long  id;
    @NotNull(message = "Client Id cannot be Null")
    Long clientId;

    @NotNull(message = "Date Should Not Be Null")
    String date;

    @NotNull(message = "Fajr Adhaan Time Should Not Be Null")
    String fajrAdhaan;

    @NotBlank(message = "Fajr Iqamah Time  Should Not Be Null")
    String fajrIqamah;


    @NotBlank(message = "Dhuhr Adahaan  Time  Should Not Be Null")
    String dhuhrAdhaan;

    @NotBlank(message = "Duhr Iqamah Time  Should Not Be Null")
    String dhuhrIqamah;

    @NotBlank(message = "Asr Adahaan  Time  Should Not Be Null")
    String asrAdhaan;

    @NotBlank(message = "Asr Iqamah Time  Should Not Be Null")
    String asrIqamah;


    @NotBlank(message = "Magrib Adahaan  Time  Should Not Be Null")
    String maghribAdhaan;

    @NotBlank(message = "Maghrib Iqamah Time  Should Not Be Null")
    String maghribIqamah;


    @NotBlank(message = "Isha Adahaan  Time  Should Not Be Null")
    String ishaAdhaan;

        @NotBlank(message = "Isha Iqamah Time  Should Not Be Null")
    String ishaIqamah;
}
