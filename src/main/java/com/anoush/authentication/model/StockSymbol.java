package com.anoush.authentication.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
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
}
