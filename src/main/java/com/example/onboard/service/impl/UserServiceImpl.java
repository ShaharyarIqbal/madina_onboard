package com.example.onboard.service.impl;

import com.example.onboard.constant.RoleName;
import com.example.onboard.constant.UserStatus;
import com.example.onboard.constant.ValidationMessage;
import com.example.onboard.domain.dto.UserDto;
import com.example.onboard.domain.model.security.Role;
import com.example.onboard.domain.model.security.UserEntity;
import com.example.onboard.domain.repository.UserRepository;
import com.example.onboard.infrastructure.exception.MadinaAppException;
import com.example.onboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper mapper;


    @Override
    public Optional<UserEntity> findByUserName(String userName) {
        log.info("getting User By name");

        Optional<UserEntity> user = userRepository.findByUserNameOrEmail(userName,userName);

        return user;
    }

    @Override
    public void addUser(UserDto userDto) {
        Optional<UserEntity> user = userRepository.findByUserNameOrEmail(userDto.getUserName(),userDto.getEmail());
        if(user.isPresent()){
            throw new EntityExistsException(ValidationMessage.USER_ALREADY_EXISTS.getName());
        }
        UserEntity userEntity = mapper.map(userDto,UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setStatus(UserStatus.ACTIVE);
        userRepository.save(userEntity);
    }


}
