package com.example.baeldung_tutorials.repositories;

import com.example.baeldung_tutorials.config.MyTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@Profile("!ci")
@SpringBootTest(webEnvironment = DEFINED_PORT)
@Import({MyTestConfiguration.class})
@EnabledIf(
        expression = "#{!environment.acceptsProfiles('ci')}",
        loadContext = true
)
class LocalCountryRepositoryTest {

    @Autowired private CountryRepository countryRepository;

    @Test
    void testFindAll() {
        var countries = this.countryRepository.findAll();
        assertFalse(countries.isEmpty());
    }

    @Test
    void testFindByName() {
        var france = this.countryRepository.findByName("France");
        assertTrue(france.isPresent());
        assertEquals("FRA",france.get().getCode());
    }

    @Test
    void testFindByCode() {
        var belgium = this.countryRepository.findByCode("BEL");
        assertTrue(belgium.isPresent());
        assertEquals("Belgium",belgium.get().getName());
    }

    @Test
    void testCountryNotFound() {
        var russia = this.countryRepository.findByCode("RUS");
        assertTrue(russia.isEmpty());
    }
}