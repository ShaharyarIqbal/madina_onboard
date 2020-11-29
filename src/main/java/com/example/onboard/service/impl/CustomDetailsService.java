package com.example.onboard.service.impl;

import com.example.onboard.domain.model.security.CustomUser;
import com.example.onboard.domain.model.security.Client;
import com.example.onboard.domain.repository.ClientRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customDetailsService")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CustomDetailsService implements UserDetailsService {

    ClientRepository oAuthRepository;

    @Override
    public UserDetails loadUserByUsername(final String usernameOrEmail) throws UsernameNotFoundException {

        Client client = oAuthRepository.findByUserName(usernameOrEmail).orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
                    );
        return CustomUser.create(client);

    }
}
