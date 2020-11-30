package com.example.onboard.domain.model;

import com.example.onboard.domain.model.security.Client;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PrayerTime extends BaseModel {


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="client_id",referencedColumnName = "id")
    private Client client;


    LocalDateTime date;


    LocalDateTime fajr_adhaan;


    LocalDateTime fajr_iqamah;



    LocalDateTime dhuhr_adhaan;


    LocalDateTime dhuhr_iqamah;


    LocalDateTime asr_adhaan;


    String asr_iqamah;


    LocalDateTime maghrib_adhaan;

    LocalDateTime maghrib_iqamah;


    LocalDateTime isha_adhaan;


    LocalDateTime isha_iqamah;
}
