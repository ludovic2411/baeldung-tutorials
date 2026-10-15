package com.example.baeldung_tutorials.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to run CI/CD tests
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(properties = {
        "jdbc:postgresql://localhost:5432/testdb",
        "spring.jpa.hibernate.ddl-auto=update",
})
@EnabledIf(
        expression = "#{environment.acceptsProfiles('ci')}",
        loadContext = true
)
class CountryRepositoryTest {

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