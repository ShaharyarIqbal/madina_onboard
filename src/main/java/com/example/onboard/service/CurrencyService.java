package com.example.onboard.service;

import com.example.onboard.domain.dto.CurrencyDto;
import com.example.onboard.domain.model.Currency;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDto> getAllCurrency();

    CurrencyDto getCurrencyById(Long id);

    CurrencyDto createCurrency(CurrencyDto currency);

    CurrencyDto deleteCurrency(Long id);


}
