package com.example.onboard.service.impl;

import com.example.onboard.constant.ValidationMessage;
import com.example.onboard.domain.dto.PrayerTimeDto;
import com.example.onboard.domain.model.PrayerTime;
import com.example.onboard.domain.model.security.Client;
import com.example.onboard.domain.repository.ClientRepository;
import com.example.onboard.domain.repository.PrayerTimeRepository;
import com.example.onboard.service.PrayerTimeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.EntityExistsException;
import java.util.Optional;

public class PrayerTimeServiceImpl implements PrayerTimeService {
    ModelMapper mapper;
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    PrayerTimeRepository prayerTimeRepository;

    @Autowired
    ClientRepository clientRepository;


    @Override
    public PrayerTime getPrayerTimeByClientId(Long id){
        Optional<PrayerTime> prayerTime = prayerTimeRepository.getPrayerTimeByClientId(id);
        if(prayerTime.isPresent()){
            throw new EntityExistsException("Prayer Time Does Not Exist");
        }
            return  prayerTime.get();
    }


    @Override
    public PrayerTime createPrayerTime(PrayerTimeDto prayerTimeDto)
    {
        PrayerTime prayerTime = mapper.map(prayerTimeDto,PrayerTime.class);

      Optional <Client> client = clientRepository.findById(prayerTimeDto.getClient_id());
        if(client.isPresent()){
            throw new EntityExistsException("Prayer Time Does Not Exist for this Client");
        }
        prayerTime.setClient(client.get());

        try{
            return prayerTimeRepository.save(prayerTime);
        }
        catch (Exception e)
        {
            throw new EntityExistsException("Prayer Time Does Not Posted Sucessfully");
        }

    }

    @Override
    public PrayerTime updatePrayerTime ( Long id , PrayerTimeDto prayerTimeDto)
    {
        PrayerTime prayerTime = mapper.map(prayerTimeDto,PrayerTime.class);
        prayerTime.setId(id);
        try{
            return prayerTimeRepository.save(prayerTime);
        }
        catch (Exception e)
        {
            throw new EntityExistsException("Prayer Time Does Not Updated Sucessfully");
        }

    }

    @Override
    public String deletePrayerTime(Long id)
    {
        try {
            prayerTimeRepository.deleteById(id);
            return id.toString();
        }
        catch(Exception e)
        {
            throw new EntityExistsException("Unable to delete Prayer Time");
        }
    }
}
