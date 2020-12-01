package com.example.onboard.domain.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyDto {
    Long id;
    String currency;
    Boolean isActive;
}