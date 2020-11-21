package com.example.onboard.service;



import com.example.onboard.domain.dto.UserDto;
import com.example.onboard.domain.model.security.UserEntity;

import java.util.Optional;

public interface UserService {

    /**
     * This method is used to find user by userName.
     *
     * @param userName
     * @return User
     */
    Optional<UserEntity> findByUserName(String userName);

    void addUser(UserDto userDto);


}
