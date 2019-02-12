package com.anoush.authentication.repository;

import com.anoush.authentication.model.StockSymbol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockSymbolRepository extends MongoRepository<StockSymbol, String> {
    Optional<StockSymbol> findBySymbol(String symbol);
}
