package com.example.baeldung_tutorials.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(properties = {
        "jdbc:postgresql://localhost:5432/testdb",
        "spring.jpa.hibernate.ddl-auto=update",
})
class PagingCountryRepositoryTest {

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