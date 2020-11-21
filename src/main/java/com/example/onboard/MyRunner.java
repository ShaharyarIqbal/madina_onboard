package com.example.onboard;



import com.example.onboard.constant.RoleName;
import com.example.onboard.domain.model.security.Role;
import com.example.onboard.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {


    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        List<Role> roles = new ArrayList<>();
        roles.add(new Role(RoleName.ADMIN,"admin roles"));
        roles.add(new Role(RoleName.USER,"user roles"));
        roleRepository.saveAll(roles);
    }
}
