package com.example.onboard.controller;

import com.example.onboard.domain.dto.CurrencyDto;
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
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CurrencyController {

    CurrencyService currencyService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<CurrencyDto> currencyList() {
        return currencyService.getAllCurrency();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public CurrencyDto createCurrency(@RequestBody @Valid CurrencyDto currencyDto) {
        return currencyService.createCurrency(currencyDto);
    }

    @GetMapping("/{id}")
    public CurrencyDto getCurrency(@PathVariable Long id) {
        return currencyService.getCurrencyById(id);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public CurrencyDto deleteCurrency(@PathVariable Long id) {
        return currencyService.deleteCurrency(id);
    }
}
