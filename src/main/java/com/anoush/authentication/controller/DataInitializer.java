package com.anoush.authentication.controller;

import com.anoush.authentication.model.*;
import com.anoush.authentication.repository.CountryRepository;
import com.anoush.authentication.repository.RoleRepository;
import com.anoush.authentication.repository.StockSymbolRepository;
import com.anoush.authentication.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    private final CountryRepository countryRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final StockSymbolRepository stockSymbolRepository;

    private TaskExecutor taskExecutor;

    @Value("${anoush.app.mongo.initialize.data}")
    private boolean load;

    @Autowired
    public DataInitializer(RoleRepository roleRepository, CountryRepository countryRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, StockSymbolRepository stockSymbolRepository, TaskExecutor taskExecutor) {
        this.roleRepository = roleRepository;
        this.countryRepository = countryRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.stockSymbolRepository = stockSymbolRepository;
        this.taskExecutor = taskExecutor;
    }

    @Override
    public void run(String... args) throws Exception {
        if (load) {
            log.info("Loading Data.");
            addUsers();
            addCountries();
            addStockSymbols();
        } else {
            log.info("Data loading is turned off.");
        }
    }

    private void addStockSymbols() {
        try {
            List<CsvBean> csvBeans = beanBuilder(Paths.get("src/main/resources/stocks/amexlist.csv"), StockSymbol.class);
            csvBeans.addAll(beanBuilder(Paths.get("src/main/resources/stocks/nasdaqlist.csv"), StockSymbol.class));
            csvBeans.addAll(beanBuilder(Paths.get("src/main/resources/stocks/nyselist.csv"), StockSymbol.class));
            csvBeans.forEach(bean -> stockSymbolRepository.save((StockSymbol) bean));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<CsvBean> beanBuilder(Path path, Class clazz) throws Exception {
        CsvTransfer csvTransfer = new CsvTransfer();
        HeaderColumnNameMappingStrategy<StockSymbol> ms = new HeaderColumnNameMappingStrategy<>();
        ms.setType(clazz);

        Reader reader = Files.newBufferedReader(path);
        CsvToBean cb = new CsvToBeanBuilder(reader)
                .withType(clazz)
                .withMappingStrategy(ms)
                .build();

        csvTransfer.setCsvList(cb.parse());
        reader.close();
        return csvTransfer.getCsvList();
    }

    private void addCountries() throws java.io.IOException {
        log.info("Adding Countries.");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Country[] countries = mapper.readValue(new File("src/main/resources/countries.json"), Country[].class);
        int i = 0;
        for (Country country : countries) {
            countryRepository.save(country);
            i++;
            if (i == countries.length) log.info("Added all the countries to the database.");
        }
    }

    private void addUsers() {
        log.info("Adding Roles.");
        for (RoleName roleName : RoleName.values()) {
            Role role = new Role(roleName);
            roleRepository.save(role);
        }
        final int range = 10;
        IntStream.range(0, 10).forEach(i -> {
            int startIndex = range * i;
            taskExecutor.execute(new ThreadedDataInitializer(roleRepository, userRepository, startIndex, range, passwordEncoder));
        });
    }
}