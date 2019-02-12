package com.anoush.authentication.controller;

import com.anoush.authentication.model.StockSymbol;
import com.anoush.authentication.repository.StockSymbolRepository;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockSymbolRepository stockSymbolRepository;

    @Autowired
    public StockController(StockSymbolRepository stockSymbolRepository) {
        this.stockSymbolRepository = stockSymbolRepository;
    }

    @GetMapping("/{symbol}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getBySymbol(@PathVariable String symbol) {
        if (Strings.isNullOrEmpty(symbol)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<StockSymbol> countryOptional = stockSymbolRepository.findBySymbol(symbol);
        return countryOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<StockSymbol>> getSymbols() {
        return ResponseEntity.ok(stockSymbolRepository.findAll());
    }
}