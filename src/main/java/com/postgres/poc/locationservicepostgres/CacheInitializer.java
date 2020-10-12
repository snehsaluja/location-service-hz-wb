package com.postgres.poc.locationservicepostgres;

import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CacheInitializer implements CommandLineRunner {

    @Autowired
    HazelcastInstance hazelcastInstance;

    @Override
    public void run(String... args) throws Exception {
        log.info("Warming up cache....");
        hazelcastInstance.getMap("Country");
    }
}
