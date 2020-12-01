package com.example.onboard.service;



import com.example.onboard.domain.dto.ClientDto;
import com.example.onboard.domain.model.security.Client;
import com.example.onboard.domain.model.security.User;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    /**
     * This method is used to find user by userName.
     *
     * @param userName
     * @return User
     */
    Optional<User>  findByUserName(String userName);

    void addUser(ClientDto clientDto);

    List<Client> getClient();


    Client getClientById(Long id);

    String deleteClient(Long id);

    Client updateClient(Long id , ClientDto clientDto);
}
