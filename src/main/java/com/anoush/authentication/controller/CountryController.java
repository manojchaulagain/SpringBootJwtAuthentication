package com.anoush.authentication.controller;

import com.anoush.authentication.model.Country;
import com.anoush.authentication.repository.CountryRepository;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/{countryName}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllCountries(@PathVariable String countryName) {
        if (Strings.isNullOrEmpty(countryName)) {
            return ResponseEntity.badRequest().build();
        }
        return countryRepository.findByName(countryName).isPresent() ?
                ResponseEntity.ok(countryRepository.findByName(countryName).get())
                : ResponseEntity.notFound().build();
    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Country>> getAllCountries() {
        return ResponseEntity.ok(countryRepository.findAll());
    }
}