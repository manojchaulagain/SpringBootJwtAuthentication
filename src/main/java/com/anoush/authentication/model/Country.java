package com.anoush.authentication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Country implements Serializable {

    @Id
    private String id;
    private String name;
    private String alpha2Code;
    private String alpha3Code;
    private List<String> callingCodes = new ArrayList<>();
    private String capital;
    private String region;
    private int population;
    private List<Double> latlng = new ArrayList<>();
    private double area;
    private List<String> timezones = new ArrayList<>();
    private List<String> borders = new ArrayList<>();
    private String nativeName;
    private String numericCode;
    private List<Currency> currencies = new ArrayList<>();
    private List<Language> languages = new ArrayList<>();
    private Map<String, String> translations = new HashMap<>();
    private String flagUrl;
    private String cioc;
}
