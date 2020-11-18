package com.example.onboard.service.impl;

import com.example.onboard.domain.model.security.CustomUser;
import com.example.onboard.domain.model.security.UserEntity;
import com.example.onboard.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customDetailsService")
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService {

    private final UserRepository oAuthRepository;

    @Override
    public UserDetails loadUserByUsername(final String usernameOrEmail) throws UsernameNotFoundException {

        UserEntity userEntity = oAuthRepository.findByUserNameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
                    );
//        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("SYSTEMADMIN");
//        grantedAuthoritiesList.add(grantedAuthority);
//        userEntity.setGrantedAuthoritiesList(grantedAuthoritiesList);
        return CustomUser.create(userEntity);

    }
}
