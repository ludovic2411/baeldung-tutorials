package com.example.baeldung_tutorials.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.baeldung_tutorials.config.MyTestConfiguration;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

/**
 * Test class that runs on an embedded container for local test
 */
@SpringBootTest(webEnvironment = DEFINED_PORT)
@Import({MyTestConfiguration.class})
@EnabledIf(
        expression = "#{!environment.acceptsProfiles('ci')}",
        loadContext = true
)
class LocalPagingCountryRepositoryTest {

    @Autowired
    private PagingCountryRepository pagingCountryRepository;

    @Test
    void testGetPage() {
        Pageable firstPageWithFiveCountries = PageRequest.of(0, 5);

        var result = pagingCountryRepository.findAll(firstPageWithFiveCountries);
        assertFalse(result.isEmpty());
        assertEquals(5, result.get().toList().size());

    }

    @Test
    void testGetPageWithItems() {
        Pageable firstPageWithFiveCountries = PageRequest.of(0, 10);

        var result = pagingCountryRepository.findAll(firstPageWithFiveCountries);
        assertFalse(result.isEmpty());
        assertEquals(10, result.get().toList().size());

    }
}