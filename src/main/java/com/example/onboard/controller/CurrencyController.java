package com.example.onboard.controller;

import com.example.onboard.domain.model.Currency;
import com.example.onboard.service.CurrencyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Currency> currencyList(){
        return currencyService.getAllCurrency();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Currency createCurrency(@RequestBody @Valid Currency currency){
        return currencyService.createCurrency(currency);
    }

    @GetMapping("/{id}")
    public Currency getCurrency(@PathVariable Long id){
        return currencyService.getCurrencyById(id);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Currency deleteCurrency(@PathVariable Long id){
        return currencyService.deleteCurrency(id);
    }
}
