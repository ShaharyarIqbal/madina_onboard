package com.example.onboard.domain.dto;

import com.example.onboard.constant.UserStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientDto {
    Long id;
    String masjidName;
    String street;
    String city;
    String state;
    String zip;
    String country;
    String website;
    String userName;
    String password;
    String contactNumber;
    String timezone;
    UserStatus status;
}
