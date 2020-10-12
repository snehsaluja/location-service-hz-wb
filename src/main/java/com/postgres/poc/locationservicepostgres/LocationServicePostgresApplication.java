package com.postgres.poc.locationservicepostgres;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.postgres.poc.locationservicepostgres.dao.CountryRepository;
import com.postgres.poc.locationservicepostgres.store.CountryMapStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

//@EnableCaching
@SpringBootApplication
public class LocationServicePostgresApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationServicePostgresApplication.class, args);
    }

    @Autowired
    CountryRepository countryRepository;

    @Bean
    public HazelcastInstance hazelcastInstance() {
        MapStoreConfig mapStoreConfig = new MapStoreConfig();
        mapStoreConfig.setEnabled(true);
        mapStoreConfig.setInitialLoadMode(MapStoreConfig.InitialLoadMode.EAGER);
        mapStoreConfig.setImplementation(new CountryMapStore(countryRepository));
        mapStoreConfig.setWriteDelaySeconds(1);
        mapStoreConfig.setWriteBatchSize(1000000);

        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Config config = xmlConfigBuilder.build();
        MapConfig mapConfig = config.getMapConfig("Country");
        mapConfig.setMapStoreConfig(mapStoreConfig);


        /*NetworkConfig network = config.getNetworkConfig();
        network.setPort(5701).setPortCount(20);
        network.setPortAutoIncrement(true);
        JoinConfig join = network.getJoin();
        join.getMulticastConfig().setEnabled(false);
        join.getTcpIpConfig()
                .addMember("machine1")
                .addMember("localhost").setEnabled(true);*/

        // Client wont be req
        //return HazelcastClient.newHazelcastClient(clientConfig);


        return Hazelcast.newHazelcastInstance(config);
    }


    /*@Bean
    public ClientConfig clientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("dev");
        return clientConfig;
    }*/
}
