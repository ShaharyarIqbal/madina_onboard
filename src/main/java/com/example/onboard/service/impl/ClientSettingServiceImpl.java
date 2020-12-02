package com.example.onboard.service.impl;

import com.example.onboard.constant.ValidationMessage;
import com.example.onboard.domain.dto.ClientDto;
import com.example.onboard.domain.dto.ClientSettingDto;
import com.example.onboard.domain.model.ClientSetting;
import com.example.onboard.domain.model.Currency;
import com.example.onboard.domain.model.security.Client;
import com.example.onboard.domain.repository.ClientRepository;
import com.example.onboard.domain.repository.ClientSettingRepository;
import com.example.onboard.domain.repository.CurrencyRepository;
import com.example.onboard.service.ClientService;
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
import javax.persistence.EntityNotFoundException;
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
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;


    @Override
    public ClientSetting createClientSetting(ClientSettingDto clientSettingDto){

        ClientSetting oldClientSetting = clientRepository.findById(clientSettingDto.getClientId()).get().getClientSetting();
        ClientSetting clientSetting = mapper.map(clientSettingDto, ClientSetting.class);

        try{

            Client client = clientRepository.findById(clientSettingDto.getClientId()).get();
//            Currency currency = currencyRepository.findById().get();

            clientSetting.setClient(client);
            Currency currency = new Currency();
            currency.setId(clientSettingDto.getCurrencyId());
            clientSetting.setCurrency(currency);
            client.setClientSetting(clientSetting);
            if(oldClientSetting!=null&&oldClientSetting.getId()!=null)
            {
                clientSetting.setId(oldClientSetting.getId());
            }
            clientRepository.save(client);
            return clientSetting;
//             return clientSettingRepository.save(clientSetting);
        }

        catch (Exception e)
        {
            throw new EntityExistsException("Client Setting Posted Successfully");
        }
    }

    @Override
    public ClientSetting clientSettingById(Long id)
    {
       Optional<Client> client = clientRepository.findById(id);

        if(!client.isPresent()|| client.get().getIsDeleted()==1)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        else{
           return client.get().getClientSetting();

        }
    }

    @Override
    public ClientSetting updateClientSetting(Long id,ClientSettingDto clientSettingDto){
        ClientSetting clientSetting = mapper.map(clientSettingDto, ClientSetting.class);
        Optional<Client> client = clientRepository.findById(clientSettingDto.getClientId());

        if(!client.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        clientSetting.setClient(client.get());
        clientSetting.setId(id);
        try{
           return clientSettingRepository.save(clientSetting);

        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @Override
    public  String deleteClientSetting(Long id){
        Optional<ClientSetting> client= clientSettingRepository.findById(id);
        if(!client.isPresent())
        {}
        client.get().setIsDeleted((byte)1);
        clientSettingRepository.save(client.get());

        return id.toString();
    }

}
