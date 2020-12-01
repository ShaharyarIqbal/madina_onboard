package com.example.onboard.service.impl;

import com.example.onboard.domain.dto.CurrencyDto;
import com.example.onboard.domain.model.Currency;
import com.example.onboard.domain.repository.CurrencyRepository;
import com.example.onboard.service.CurrencyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CurrencyServiceImpl implements CurrencyService {

    CurrencyRepository currencyRepository;

    ModelMapper mapper;

    @Override
    public List<CurrencyDto> getAllCurrency() {
        return currencyRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .filter(Currency::getIsActive)
                .map(currency -> mapper.map(currency,CurrencyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CurrencyDto getCurrencyById(Long id) {
        return currencyRepository.findById(id).map(currency -> mapper.map(currency,CurrencyDto.class))
                .orElseThrow(() -> new EntityNotFoundException("No Currency Found"));
    }

    @Override
    public CurrencyDto createCurrency(CurrencyDto currencyDto) {
        Currency currency = mapper.map(currencyDto,Currency.class);
        Currency savedCurrency = currencyRepository.save(currency);
        return mapper.map(savedCurrency,CurrencyDto.class);
    }

    @Override
    public CurrencyDto deleteCurrency(Long id) {
        Currency currency = currencyRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No Currency Found"));
        currency.setIsDeleted((byte) 1);
        Currency savedCurrency = currencyRepository.save(currency);
        return mapper.map(savedCurrency,CurrencyDto.class);
    }
}
