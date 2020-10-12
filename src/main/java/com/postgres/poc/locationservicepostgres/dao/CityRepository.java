package com.postgres.poc.locationservicepostgres.dao;

import com.postgres.poc.locationservicepostgres.model.City;
import com.postgres.poc.locationservicepostgres.model.CityKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, CityKey> {

    public List<City> findByPopulationGreaterThanEqualOrderByPopulationDesc(int population);

    /*@Query("SELECT city.name, MAX(city.population), country.name, country.GovernmentForm FROM country " +
            "JOIN city ON city.countrycode = country.code " +
            "GROUP BY city.name, country.name, country.GovernmentForm, city.population " +
            "ORDER BY city.population DESC :limit ?")
    public List<City> findMostPopulatedCities(@Param("limit") int limit);*/

    @Query("SELECT c FROM City c where id.ID = :id")
    public City findById(int id);

}

