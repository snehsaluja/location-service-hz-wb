package com.postgres.poc.locationservicepostgres.dao;

import com.postgres.poc.locationservicepostgres.model.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {

    public List<Country> findByPopulationGreaterThanEqualOrderByPopulationDesc(int population);

    @Query("SELECT c.code FROM Country c")
    public List<String> findAllIds();

}
