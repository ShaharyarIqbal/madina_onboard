package com.example.onboard.controller;

import com.example.onboard.constant.UserStatus;
import com.example.onboard.domain.dto.ClientDto;
import com.example.onboard.domain.dto.ClientSettingDto;
import com.example.onboard.domain.model.ClientSetting;
import com.example.onboard.domain.model.security.Client;
import com.example.onboard.infrastructure.security.constant.SpringSecurity;
import com.example.onboard.service.ClientService;
import com.example.onboard.service.ClientSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
//@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientSettingService clientSettingService;

    @GetMapping(value = "client/")
    public List<Client> clientList(){

        return clientService.getClient();
    }

    @GetMapping(value = "client/{id}")
    public Client getClientById(@PathVariable("id") Long id)
    {
       return clientService.getClientById(id);

    }
    @DeleteMapping(value = "client/{id}")
    public String deleteClient(@PathVariable("id")Long id)
    {
        return clientService.deleteClient(id);
    }

    @PutMapping(value = "client/{id}")
    public Client updateClient(@PathVariable ("id") Long id, @RequestBody ClientDto clientDto)
    {
        return clientService.updateClient(id,clientDto);
    }


    @GetMapping(value = "clientsetting/{id}")
    public ClientSetting getClientSetting(@PathVariable("id")Long id)
    {
        return  clientSettingService.clientSettingById(id);
    }

    @PutMapping (value = "clientsetting/{id}")
    public ClientSetting updateClientSetting(@PathVariable("id")Long id, @RequestBody ClientSettingDto clientSettingDto)
    {
        return  clientSettingService.updateClientSetting(id,clientSettingDto);
    }

    @PostMapping(value = "clientsetting/")
    public  ClientSetting createClientSetting (@RequestBody ClientSettingDto clientSettingDto)
    {
        return clientSettingService.createClientSetting(clientSettingDto);
    }

    @DeleteMapping(value = "/clientsetting/{id}")
    public  String deleteClientSetting(@PathVariable("id") Long id)
    {
        return clientSettingService.deleteClientSetting(id);
    }



}
