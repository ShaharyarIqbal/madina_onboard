package com.example.onboard.service.impl;

import com.example.onboard.constant.ValidationMessage;
import com.example.onboard.domain.dto.ClientDto;
import com.example.onboard.domain.dto.ClientSettingDto;
import com.example.onboard.domain.model.ClientSetting;
import com.example.onboard.domain.model.security.Client;
import com.example.onboard.domain.repository.ClientSettingRepository;
import com.example.onboard.service.ClientSettingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientSettingServiceImpl implements ClientSettingService {
    ModelMapper mapper;

    @Autowired
    ClientSettingRepository clientSettingRepository;


    @Override
    public ClientSetting createClientSetting(ClientSettingDto clientSettingDto){
        ClientSetting clientSetting = mapper.map(clientSettingDto, ClientSetting.class);

        try{

        return clientSettingRepository.save(clientSetting);
        }

        catch (Exception e)
        {
            throw new EntityExistsException("Client Setting Posted Successfully");
        }
    }

    @Override
    public ClientSetting clientSettingById(Long id)
    {
        Optional<ClientSetting>  clientSetting = clientSettingRepository.findById(id);

        if(!clientSetting.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        return clientSetting.get();
    }

    @Override
    public ClientSetting updateClientSetting(Long id,ClientSettingDto clientSettingDto){
        ClientSetting clientSetting = mapper.map(clientSettingDto, ClientSetting.class);
        clientSetting.setId(id);
        try{
           return clientSettingRepository.save(clientSetting);

        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
