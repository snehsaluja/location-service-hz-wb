package com.postgres.poc.locationservicepostgres.store;

import com.hazelcast.map.MapStore;
import com.postgres.poc.locationservicepostgres.dao.CountryRepository;
import com.postgres.poc.locationservicepostgres.model.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CountryMapStore implements MapStore<String, Country> {

    private CountryRepository countryRepository;

    public CountryMapStore(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void store(String key, Country value) {
        StopWatch watch = new StopWatch("StoreSingleEntry.StopWatch");
        watch.start("StoreSingleEntryToDB");
        countryRepository.save(value);
        watch.stop();
        log.info("Total Time = {} (milis)", watch.getTotalTimeMillis());
        log.info(watch.prettyPrint());
    }

    @Override
    public void storeAll(Map<String, Country> map) {
        //log.info("Map size = {}", map.size());
        //StopWatch watch = new StopWatch("StoreAll.StopWatch");
        //watch.start("StoreAllToDB");
        countryRepository.saveAll(map.values());
        //watch.stop();
        //log.info("Total Time = {} (milis)", watch.getTotalTimeMillis());
        //log.info(watch.prettyPrint());
    }

    @Override
    public void delete(String key) {
        countryRepository.deleteById(key);
    }

    @Override
    public void deleteAll(Collection<String> keys) {
        countryRepository.deleteAll();
    }

    @Override
    public Country load(String key) {
        Country country = countryRepository.findById(key).orElse(null);
        return country;

    }

    @Override
    public Map<String, Country> loadAll(Collection<String> keys) {
        log.info("Loading all the data from DB to Cache");
        StopWatch watch = new StopWatch("LoadAll.StopWatch");
        watch.start("loadAllFromDB");
        Iterable<Country> countries = countryRepository.findAllById(keys);
        Map<String, Country> countryMap = new HashMap<>();
        for (Country country : countries) {
            countryMap.put(country.getCode(), country);
        }
        watch.stop();
        log.info("Total Time = {} (milis)", watch.getTotalTimeMillis());
        log.info(watch.prettyPrint());
        return countryMap;
    }

    @Override
    public Iterable<String> loadAllKeys() {
        return countryRepository.findAllIds();
    }
}
