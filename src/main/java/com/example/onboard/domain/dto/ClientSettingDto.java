package com.example.onboard.domain.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientSettingDto {
    Long id;
    Long clientId;
    Double latitude;
    Double longitude;
    String calculationMethod;
    String juristicMethod;
    Long currencyId;
    CurrencyDto currencyDto;
}
