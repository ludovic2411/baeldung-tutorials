package com.example.baeldung_tutorials.config;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public interface LocalTestContainers {
    @Container
    @ServiceConnection// tell Spring to use container connections details for datasource
    static PostgreSQLContainer postgreSQLContainer= new PostgreSQLContainer("postgres:latest");
}
