package com.example.onboard.service;

import com.example.onboard.domain.model.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> getAllCurrency();

    Currency getCurrencyById(Long id);

    Currency createCurrency(Currency currency);

    Currency deleteCurrency(Long id);


}
