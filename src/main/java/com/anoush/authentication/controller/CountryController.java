package com.anoush.authentication.controller;

import com.anoush.authentication.model.Country;
import com.anoush.authentication.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/api/countries/{countryName}")
    @PreAuthorize("hasRole('USER')")
    public Country getAllCountries(@PathVariable String countryName) {
        return countryRepository.findByName(countryName).isPresent() ? countryRepository.findByName(countryName).get() : null;
    }

    @GetMapping("/api/countries")
    @PreAuthorize("hasRole('USER')")
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}