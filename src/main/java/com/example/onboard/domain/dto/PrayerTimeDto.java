package com.example.onboard.domain.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrayerTimeDto {


    Long  id;
    @NotNull(message = "Client Id cannot be Null")
    Long client_id;

    @NotNull(message = "Date Should Not Be Null")
    LocalDateTime date;

    @NotNull(message = "Fajr Adhaan Time Should Not Be Null")
    LocalDateTime fajr_adhaan;

    @NotBlank(message = "Fajr Iqamah Time  Should Not Be Null")
    LocalDateTime fajr_iqamah;


    @NotBlank(message = "Dhuhr Adahaan  Time  Should Not Be Null")
    LocalDateTime dhuhr_adhaan;

    @NotBlank(message = "Duhr Iqamah Time  Should Not Be Null")
    LocalDateTime dhuhr_iqamah;

    @NotBlank(message = "Asr Adahaan  Time  Should Not Be Null")
    LocalDateTime asr_adhaan;

    @NotBlank(message = "Asr Iqamah Time  Should Not Be Null")
    String asr_iqamah;


    @NotBlank(message = "Magrib Adahaan  Time  Should Not Be Null")
    LocalDateTime maghrib_adhaan;

    @NotBlank(message = "Maghrib Iqamah Time  Should Not Be Null")
    LocalDateTime maghrib_iqamah;


    @NotBlank(message = "Isha Adahaan  Time  Should Not Be Null")
    LocalDateTime isha_adhaan;

    @NotBlank(message = "Isha Iqamah Time  Should Not Be Null")
    LocalDateTime isha_iqamah;
}
