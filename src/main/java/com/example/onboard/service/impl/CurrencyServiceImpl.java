package com.example.onboard.service.impl;

import com.example.onboard.domain.model.Currency;
import com.example.onboard.domain.repository.CurrencyRepository;
import com.example.onboard.service.CurrencyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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

    @Override
    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .filter(Currency::getIsActive)
                .collect(Collectors.toList());

    }

    @Override
    public Currency getCurrencyById(Long id) {
        return null;
    }

    @Override
    public Currency createCurrency(Currency currency) {
        return null;
    }

    @Override
    public Currency deleteCurrency(Long id) {
        return null;
    }
}