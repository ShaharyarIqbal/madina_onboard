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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrayerTimeServiceImpl implements PrayerTimeService {
    ModelMapper mapper;
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    PrayerTimeRepository prayerTimeRepository;

    @Autowired
    ClientRepository clientRepository;


    @Override
    public List<PrayerTime> getPrayerTimeByClientId(Long id){
//        List<PrayerTime> prayerTime =
                return prayerTimeRepository.getPrayerTimeByClientId(id).stream().filter(p->p.getIsDeleted()==0).collect(Collectors.toList());
//        if(!prayerTime.isPresent()){
//            throw new EntityExistsException("Prayer Time Does Not Exist");
//        }
//       PrayerTimeDto prayerTimeDto= mapper.map(prayerTime.get(),PrayerTimeDto.class);
//            return  prayerTimeDto;
    }


    @Override
    public PrayerTime createPrayerTime(PrayerTimeDto prayerTimeDto)
    {
//        PrayerTime prayerTime = mapper.map(prayerTimeDto,PrayerTime.class);

        DateFormat formatter = new SimpleDateFormat("HH:mm");
        PrayerTime prayerTime = new PrayerTime();
        prayerTime.setFajr_adhaan(LocalTime.parse(prayerTimeDto.getFajrAdhaan()));
        prayerTime.setFajr_iqamah(LocalTime.parse(prayerTimeDto.getFajrIqamah()));
        prayerTime.setDhuhr_adhaan(LocalTime.parse(prayerTimeDto.getDhuhrAdhaan()));
        prayerTime.setDhuhr_iqamah(LocalTime.parse(prayerTimeDto.getDhuhrIqamah()));
        prayerTime.setAsr_adhaan(LocalTime.parse(prayerTimeDto.getAsrAdhaan()));
        prayerTime.setAsr_iqamah(LocalTime.parse(prayerTimeDto.getAsrIqamah()));
        prayerTime.setMaghrib_adhaan(LocalTime.parse(prayerTimeDto.getMaghribAdhaan()));
        prayerTime.setMaghrib_iqamah(LocalTime.parse(prayerTimeDto.getMaghribIqamah()));
        prayerTime.setIsha_adhaan(LocalTime.parse(prayerTimeDto.getIshaAdhaan()));
        prayerTime.setIsha_iqamah(LocalTime.parse(prayerTimeDto.getIshaIqamah()));
        prayerTime.setDate(LocalDate.parse(prayerTimeDto.getDate()));


        Optional <Client> client = clientRepository.findById(prayerTimeDto.getClientId());
        if(!client.isPresent()){
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
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        PrayerTime prayerTime = new PrayerTime();
        prayerTime.setFajr_adhaan(LocalTime.parse(prayerTimeDto.getFajrAdhaan()));
        prayerTime.setFajr_iqamah(LocalTime.parse(prayerTimeDto.getFajrIqamah()));
        prayerTime.setDhuhr_adhaan(LocalTime.parse(prayerTimeDto.getDhuhrAdhaan()));
        prayerTime.setDhuhr_iqamah(LocalTime.parse(prayerTimeDto.getDhuhrIqamah()));
        prayerTime.setAsr_adhaan(LocalTime.parse(prayerTimeDto.getAsrAdhaan()));
        prayerTime.setAsr_iqamah(LocalTime.parse(prayerTimeDto.getAsrIqamah()));
        prayerTime.setMaghrib_adhaan(LocalTime.parse(prayerTimeDto.getMaghribAdhaan()));
        prayerTime.setMaghrib_iqamah(LocalTime.parse(prayerTimeDto.getMaghribIqamah()));
        prayerTime.setIsha_adhaan(LocalTime.parse(prayerTimeDto.getIshaAdhaan()));
        prayerTime.setIsha_iqamah(LocalTime.parse(prayerTimeDto.getIshaIqamah()));
        prayerTime.setDate(LocalDate.parse(prayerTimeDto.getDate()));
        prayerTime.setId(id);
        Optional <Client> client = clientRepository.findById(prayerTimeDto.getClientId());
        if(!client.isPresent()){
            throw new EntityExistsException("Prayer Time Does Not Exist for this Client");
        }
        prayerTime.setClient(client.get());
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
        PrayerTime   prayerTime = prayerTimeRepository.findById(id).orElseThrow(() ->
                     new EntityNotFoundException(ValidationMessage.ENTITY_NOT_FOUND.getName()));



        prayerTime.setIsDeleted((byte) 1);
        prayerTimeRepository.save(prayerTime);
        return id.toString();




    }
}
