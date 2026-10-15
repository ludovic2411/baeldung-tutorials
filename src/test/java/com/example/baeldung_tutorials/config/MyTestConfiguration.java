package com.example.baeldung_tutorials.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;

@TestConfiguration(proxyBeanMethods = false)
@ImportTestcontainers(LocalTestContainers.class)
public class MyTestConfiguration {

}
