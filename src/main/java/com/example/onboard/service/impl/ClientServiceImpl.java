package com.example.onboard.service.impl;

import com.example.onboard.constant.RoleName;
import com.example.onboard.constant.UserStatus;
import com.example.onboard.constant.ValidationMessage;
import com.example.onboard.domain.dto.ClientDto;
import com.example.onboard.domain.model.security.Role;
import com.example.onboard.domain.model.security.User;
import com.example.onboard.domain.model.security.Client;
import com.example.onboard.domain.repository.RoleRepository;
import com.example.onboard.domain.repository.ClientRepository;
import com.example.onboard.domain.repository.UserRepository;
import com.example.onboard.infrastructure.security.constant.SpringSecurity;
import com.example.onboard.service.ClientService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;

    RoleRepository roleRepository;

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    ModelMapper mapper;


    @Override
    public Optional<User> findByUserName (String userName) {
        log.info("getting User By name");

        Optional<User> user = userRepository.findByUserName(userName);

        return user;
    }

    @Override
    public void addUser(ClientDto clientDto) {
        Optional<Client> user = clientRepository.findByUserName(clientDto.getUserName());
        if(user.isPresent()){
            throw new EntityExistsException(ValidationMessage.USER_ALREADY_EXISTS.getName());
        }

        Client client = mapper.map(clientDto, Client.class);
        client.setPassword(passwordEncoder.encode(clientDto.getPassword()));
        client.setStatus(UserStatus.ACTIVE);

        User user1 =  new User();
        user1.setUserName(clientDto.getUserName());
        user1.setPassword(passwordEncoder.encode(clientDto.getPassword()));
        user1.setStatus(UserStatus.ACTIVE);
        userRepository.save(user1);
//


        //Assign "User" role to user
        Set<Role> role = roleRepository.findByName(RoleName.USER);
        client.setRoles(role);

        clientRepository.save(client);
    }


    @Override
    public List<Client> getClient(){
       return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id){
        Optional<Client> client= clientRepository.findById(id);

        if(client.isPresent() && client.get().getStatus()== UserStatus.INACTIVE)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, SpringSecurity.ACCOUNT_INACTIVE.getValue());
        }
        return client.get();

    }

    @Override
    public String deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(()->  new EntityNotFoundException(ValidationMessage.USER_NOT_FOUND.getName()));

//        Client updatedClient = client.get();
        client.setIsDeleted((byte)1);
        client.setStatus(UserStatus.INACTIVE);
        User user = userRepository.findById(id).get();
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
        clientRepository.save(client);
        return id.toString();

    }
    @Override
    public  Client updateClient(Long id,ClientDto clientDto)
    {
        Client client = mapper.map(clientDto, Client.class);
        client.setId(id);
        try{

            clientRepository.save(client);
            return client;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
