package com.example.onboard.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    private Long id;
    @NotNull(message = "UserName Should Not Be Null")
    private String userName;

    private String password;

    @NotNull(message = "emailAddress Should Not Be Null")
    @Size(min = 3, max = 254)
    @Email(message = "Please insert a valid emailAddress.")
    private String email;

    private String firstName;

    private int age;
}
