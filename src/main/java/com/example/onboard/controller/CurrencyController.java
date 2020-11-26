package com.example.onboard.controller;

import com.example.onboard.domain.model.Currency;
import com.example.onboard.service.CurrencyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/currency", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CurrencyController {

    CurrencyService currencyService;

    @GetMapping
    public List<Currency> currencyList(){
        return currencyService.getAllCurrency();
    }

    @PostMapping
    public Currency createCurrency(@RequestBody @Valid Currency currency){
        return currencyService.createCurrency(currency);
    }
}
