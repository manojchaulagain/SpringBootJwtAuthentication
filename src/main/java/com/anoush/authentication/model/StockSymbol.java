package com.anoush.authentication.model;

import com.opencsv.bean.CsvBindByName;
import org.springframework.data.annotation.Id;

public class StockSymbol extends CsvBean {

    @Id
    private String id;
    @CsvBindByName(column = "Symbol")
    private String symbol;
    @CsvBindByName(column = "Name")
    private String name;
    @CsvBindByName(column = "LastSale")
    private String lastSale;
    @CsvBindByName(column = "MarketCap")
    private String marketCap;
    @CsvBindByName(column = "ADR TSO")
    private String adrTso;
    @CsvBindByName(column = "IPOyear")
    private String ipoYear;
    @CsvBindByName(column = "Sector")
    private String sector;
    @CsvBindByName(column = "Industry")
    private String industry;
    @CsvBindByName(column = "Summary Quote")
    private String summaryQuote;

    public StockSymbol() {
    }

    public StockSymbol(String id, String symbol, String name, String lastSale, String marketCap, String adrTso, String ipoYear, String sector, String industry, String summaryQuote) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.lastSale = lastSale;
        this.marketCap = marketCap;
        this.adrTso = adrTso;
        this.ipoYear = ipoYear;
        this.sector = sector;
        this.industry = industry;
        this.summaryQuote = summaryQuote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastSale() {
        return lastSale;
    }

    public void setLastSale(String lastSale) {
        this.lastSale = lastSale;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getAdrTso() {
        return adrTso;
    }

    public void setAdrTso(String adrTso) {
        this.adrTso = adrTso;
    }

    public String getIpoYear() {
        return ipoYear;
    }

    public void setIpoYear(String ipoYear) {
        this.ipoYear = ipoYear;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSummaryQuote() {
        return summaryQuote;
    }

    public void setSummaryQuote(String summaryQuote) {
        this.summaryQuote = summaryQuote;
    }
}
