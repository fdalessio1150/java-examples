package com.example.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;

@Configuration
public class ApplicationConfiguration {

	@Bean
    @Lazy
    public Cluster cluster(@Value("${cassandra.cluster}") String cluster,
                           @Value("${cassandra.addresses}") String[] adresses,
                           @Value("${cassandra.port}") Integer port,
                           @Value("${cassandra.username}") String username,
                           @Value("${cassandra.password}") String password) {
        return Cluster.builder()
                .withCredentials(username, password)
                .withClusterName(cluster)
                .addContactPoints(adresses)
                .withPort(port)
                .build();
    }

    @Bean
    @Lazy
    public MappingManager manager(Cluster cluster, @Value("${cassandra.keyspace}") String keyspace) {
        Session session = cluster.connect(keyspace);
        return new MappingManager(session);
    }

}
