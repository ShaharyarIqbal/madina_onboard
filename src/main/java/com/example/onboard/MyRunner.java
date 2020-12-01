package com.example.onboard;



import com.example.onboard.constant.RoleName;
import com.example.onboard.domain.model.Currency;
import com.example.onboard.domain.model.security.Role;
import com.example.onboard.domain.repository.CurrencyRepository;
import com.example.onboard.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {


    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CurrencyRepository currencyRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    String ddlAuto;


    @Override
    public void run(String... args) throws Exception {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(RoleName.ADMIN,"admin roles"));
        roles.add(new Role(RoleName.USER,"user roles"));
        Currency currency = new Currency();
        currency.setCurrency("USD");
        currency.setIsActive(true);
        if(ddlAuto.equals("create")){
            roleRepository.saveAll(roles);
            currencyRepository.save(currency);
        }
    }
}
